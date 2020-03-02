package com.cpd.repository;

import com.cpd.entity.nodes.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cfg {

    @Autowired
    private ConfigRepository configRepository;

    public String getCfg(String param) {
        Config cfg = configRepository.get();
        switch (param) {
            case "id":
                return String.valueOf(cfg.getId());
            case "anoLetivo":
                return String.valueOf(cfg.getAnoLetivo());
            case "escolaInep":
                return String.valueOf(cfg.getEscolaInep());
            case "ultimaAtualizacao":
                return cfg.getUltimaAtualizacao();
            default:
                return "";
        }
    }

    public Config setCfg(String param, String val) {
        Config cfg = configRepository.get();
        switch (param) {
            case "anoLetivo":
                cfg.setAnoLetivo(Integer.parseInt(val));
                break;
            case "escolaInep":
                cfg.setEscolaInep(Long.parseLong(val));
                break;
            case "ultimaAtualizacao":
                cfg.setUltimaAtualizacao(val);
                break;
            default:
                break;
        }
        configRepository.save(cfg);
        return cfg;
    }
}