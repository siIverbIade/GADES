package com.cpd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cpd.entity.nodes.Config;
import com.cpd.model.Labeled;
import com.cpd.repository.AuditRepository;
import com.cpd.repository.ConfigRepository;
import com.cpd.utils.Debug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class AtualizacaoAgendada {

	private static final Logger log = LoggerFactory.getLogger(AtualizacaoAgendada.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Autowired
	private AuditRepository auditRepository;

	@Autowired
	private ConfigRepository cr;

	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime() {
		System.out.println("Rotina de atualização iniciada às " + dateFormat.format(new Date()) + " ...");
		String uri = "http://localhost/GADES_MASTER/";
		Config cfg = cr.get();
		String ultima = cfg.getUltimaAtualizacao();
		List<Labeled> ll = auditRepository.getFlush(ultima);
		String UploadJson = Debug.Print(ll);
		int total = ll.size();
		if (total == 0) {
			System.out.println("Não existem dados novos a serem enviados.");
		} else {
			System.out.println("Detectadas " + total + " alterações realizadas desde a última vez em " + ultima);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> entity = new HttpEntity<String>(UploadJson, headers);
			try {
				System.out.println("Tentando se conectar à " + uri);
				String answer = restTemplate.postForObject(uri, entity, String.class);
				System.out.println(answer);
				cfg.setUltimaAtualizacao(dateFormat.format(new Date()));
				cr.save(cfg);

			} catch (RestClientException e) {
				System.out.println(e.getMessage());
			}

			log.info("Rotina de atualização terminada às {}", dateFormat.format(new Date()));
		}
	}
}