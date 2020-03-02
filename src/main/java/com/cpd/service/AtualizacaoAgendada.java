package com.cpd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private AuditRepository auditRepository;
	
	@Autowired
	private ConfigRepository cr;

	@Scheduled(fixedRate = 60000)
	public void reportCurrentTime() {
		List<Labeled> ll = auditRepository.getFlush("10/02/2020");
		String UploadJson = Debug.Load(ll);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>(UploadJson,headers);
		try {
			String answer = restTemplate.postForObject("http://localhost/GADES_MASTER/", entity, String.class);
			System.out.println(answer);

		} catch (RestClientException e) {
			System.out.println(e.getMessage());
		}

		log.info("UPLOAD TERMINADO {}", dateFormat.format(new Date()));
	}
}