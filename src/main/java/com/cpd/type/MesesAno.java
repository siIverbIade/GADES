package com.cpd.type;

import com.cpd.utils.TextUtils;

public enum MesesAno {
  JAN("Janeiro"), FEV("Fevereiro"), MAR("Março"), ABR("Abril"), MAI("Maio"), JUN("Junho"), JUL("Julho"), AGO("Agosto"),
  SET("Setembro"), OUT("Outubro"), NOV("Novembro"), DEZ("Dezembro");

  private final String text_full;

  MesesAno(String text_full) {
    this.text_full = text_full;
  }

  MesesAno() {
    this.text_full = "";
  }

  public String FormatoCompleto() {
    return (text_full == "") ? this.toString() : text_full;
  }

  public String FormatoAbreviado() {
    return TextUtils.firstLetterUp(this.toString());
  }

}