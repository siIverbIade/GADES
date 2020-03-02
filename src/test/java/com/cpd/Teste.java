package com.cpd;

import com.cpd.utils.DateUtils;
import com.cpd.utils.Debug;

public class Teste {
    public static void main(String[] args) {
        Debug.Print(DateUtils.now().getMillis());

        /*
         * try { ScriptEngineManager manager = new ScriptEngineManager(); ScriptEngine
         * engine = manager.getEngineByName("javascript");
         * 
         * String JsFunction1 = "function data(Y, R) {return ('1/5/' + Y);};";
         * 
         * engine.eval(JsFunction1); try { Invocable inv = (Invocable) engine;
         * System.out.println("Retorno:" + inv.invokeFunction("data", 2020, 0)); } catch
         * (NoSuchMethodException e) { e.getMessage(); } } catch (ScriptException e) {
         * e.getMessage(); }
         */
    }
}