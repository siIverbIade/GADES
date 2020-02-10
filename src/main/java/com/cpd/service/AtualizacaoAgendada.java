package com.cpd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.cpd.entity.nodes.Localidade;
import com.cpd.repository.LocalidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

@Component
public class AtualizacaoAgendada {

	private static final Logger log = LoggerFactory.getLogger(AtualizacaoAgendada.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	private LocalidadeRepository localidadeRepository;

	//@Scheduled(fixedRate = 180000)
	public void reportCurrentTime() {
		//RestTemplate restTemplate = new RestTemplate();
		Iterable<Localidade> mun = localidadeRepository.findAll();
		
		mun.forEach(m -> {
			/*String sql = "INSERT INTO localidade (id, cod, nome) VALUES (" + m.getId() + ", " + m.getCod() + ", \""
					+ m.getNome() + "\")";*/
			try {
				//System.out.println(sql);
				//System.out.println(restTemplate.getForEntity("http://192.168.10.106/teste/inserteste?"+sql, String.class));
			} catch (RestClientException e) {
				System.out.println(e.getMessage());
			}
		});
		log.info("UPLOAD TERMINADO {}", dateFormat.format(new Date()));
	}
}