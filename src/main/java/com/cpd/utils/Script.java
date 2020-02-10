package com.cpd.utils;

import javax.script.*;

public class Script {
    public static Invocable RunJs(String jsCode) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval(jsCode);
            Invocable inv = (Invocable) engine;
            return inv;
        } catch (ScriptException e) {
            e.printStackTrace();
            return null;
        }

    }
}