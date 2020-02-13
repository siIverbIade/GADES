package com.cpd.entity.nodes;

import javax.script.ScriptException;
import com.cpd.utils.Script;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.neo4j.ogm.annotation.NodeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@NodeEntity
@Data
@EqualsAndHashCode(callSuper = false)
public class RotuloCalendario extends Base {
    private boolean temAula;
    private boolean global;
    private String simbolo;
    private String descricao;
    private String foreColor;
    private String backColor;
    private String jsRegra;

    public RotuloCalendario(String nome, boolean temAula, boolean global, String simbolo, String descricao,
            String foreColor, String backColor, String jsRegra) {
        this.setNome(nome);
        this.temAula = temAula;
        this.global = global;
        this.simbolo = simbolo;
        this.descricao = descricao;
        this.foreColor = foreColor;
        this.backColor = backColor;
        this.jsRegra = jsRegra;
    }

    public void setSingleDate(int dia, int mes) {
        this.jsRegra = "function data(Y, R) {return ('" + dia + "/" + mes + "/' + Y);};";
    }

    public void setRule(String jsRegra) {
        try {
            Script.RunJs(jsRegra).invokeFunction("data", 1900, 0).toString();
            this.jsRegra = jsRegra;
        } catch (NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
            System.out.println("O argumento String não passou uma função Javascript em formato válido.");
            this.jsRegra = "";
        }

    }

    public String getDates(int ano, int ord) {
        if (ord > 0) {
            try {
                String data = Script.RunJs(jsRegra).invokeFunction("data", ano, ord).toString();
                DateTime date = DateTime.parse(data, DateTimeFormat.forPattern("dd/MM/yyyy"));
                return date.toString("dd/MM/yyyy");
            } catch (NoSuchMethodException | ScriptException e) {
                e.printStackTrace();
                return jsRegra;
            }
        } else {
            return "";
        }
    }

    public int getTotalDates(int ano) {
        try {
            System.out.println(getNome());
            String str0 = Script.RunJs(jsRegra).invokeFunction("data", ano, 0).toString();
            String str1 = Script.RunJs(jsRegra).invokeFunction("data", ano, 1).toString();

            if (str0.equalsIgnoreCase(str1)) {
                return 1;
            } else {
                return Math.round(Float.parseFloat(str0));
            }
        } catch (NumberFormatException | NoSuchMethodException | ScriptException e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }
}