package com.cpd.repository;

import java.util.ArrayList;
import java.util.List;
import com.cpd.model.Labeled;
import com.cpd.utils.DateUtils;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuditRepository {
	@Autowired
	private Driver db;

	public List<Labeled> getFlush(String data) {
		List<Labeled> lista = new ArrayList<Labeled>();
		System.out.println(DateUtils.getString(data).getMillis());

		try (Session session = db.session()) {
			// Auto-commit transactions are a quick and easy way to wrap a read.
			StatementResult result = session.run("MATCH (m) WHERE m.dataCriacao > " + DateUtils.getString(data).getMillis()
					+ " or m.dataModificado > " + DateUtils.getString(data).getMillis() + " RETURN m as nodeId, labels(m)[0] as nodeLabel");
			result.list().forEach(e -> {
				Labeled l = new Labeled();
				l.setTabela(e.get("nodeLabel").asString());
				l.setCampo_valor(e.get("nodeId").asMap());
				lista.add(l);
				e.get("nodeId").asMap().keySet().forEach(k -> {
				});
			});
		}
		return lista;
	}
}
