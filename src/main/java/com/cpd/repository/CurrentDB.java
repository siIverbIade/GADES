package com.cpd.repository;

import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Service;
import static org.neo4j.driver.v1.Values.parameters;
import java.util.List;

@Service
public class CurrentDB {
	// Driver objects are thread-safe and are typically made available
	// application-wide.
	Driver driver;

	private final String uri = "bolt://localhost:7687";
	private final String user = "neo4j";
	private final String password = "tecanexo123";
	private StatementResult result;

	public CurrentDB() {
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	public CurrentDB(String uri, String user, String password) {
		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
	}

	public void Execute(String query, String parameter, String value) {
		// Sessions are lightweight and disposable connection wrappers.
		try (Session session = driver.session()) {
			// Wrapping Cypher in an explicit transaction provides atomicity
			// and makes handling errors much easier.
			try (Transaction tx = session.beginTransaction()) {
				tx.run(query, parameters(parameter, value));
				tx.success(); // Mark this write as successful.
			}
		}
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

	public void close() {
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