package com.cpd.repository;

import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static org.neo4j.driver.v1.Values.parameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import com.cpd.utils.Debug;


@Component
public class CurrentDB {
	// Driver objects are thread-safe and are typically made available
	// application-wide.
	@Autowired
	Driver driver;

	private StatementResult result;

	public String loadCypherJSON(String query, String returns) {
		List<Record> registros = OpenResult(query);
		List<Map<String, Object>> queryMap = new ArrayList<Map<String, Object>>();
		
		try {
			IntStream.range(0, registros.size()).forEach(i -> {queryMap.add(registros.get(i).get(returns).asMap());});
		} catch (Exception e) {
			System.out.print("CDB: " + e.getMessage());
		}
		return Debug.Print(queryMap);
	}

	public List<Map<String, Object>> loadCypher(String query, String returns) {
		List<Record> registros = OpenResult(query);
		List<Map<String, Object>> queryMap = new ArrayList<Map<String, Object>>();
		
		try {
			IntStream.range(0, registros.size()).forEach(i -> {queryMap.add(registros.get(i).get(returns).asMap());});
		} catch (Exception e) {
			System.out.print("CDB: " + e.getMessage());
		}
		return queryMap;
	}

	public void Execute(String query) {
		// Sessions are lightweight and disposable connection wrappers.
		try (Session session = driver.session()) {
			// Wrapping Cypher in an explicit transaction provides atomicity
			// and makes handling errors much easier.
			try (Transaction tx = session.beginTransaction()) {
				tx.run(query);
				tx.success(); // Mark this write as successful.
			}
		}
	}

	public StatementResult OpenResult(String query, String parameter, String value) {

		try (Session session = driver.session()) {
			// Auto-commit transactions are a quick and easy way to wrap a read.
			StatementResult result = session.run(query, parameters(parameter, value));
			this.setResult(result);
			// Each Cypher execution returns a stream of records.
		}
		return this.result;
	};

	public List<Record> OpenResult(String query) {

		try (Session session = driver.session()) {
			// Auto-commit transactions are a quick and easy way to wrap a read.
			StatementResult result = session.run(query);
			this.setResult(result);
			// Each Cypher execution returns a stream of records.
		}
		return this.result.list();
	};

	public void close() throws Exception {
		// Closing a driver immediately shuts down all open connections.
		driver.close();
	}

	public void setResult(StatementResult result) {
		this.result = result;
	}

	public StatementResult getResult() {
		return this.result;
	}
}