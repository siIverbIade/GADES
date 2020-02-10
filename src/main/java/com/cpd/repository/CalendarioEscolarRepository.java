package com.cpd.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.cpd.entity.nodes.CalendarioEscolar;

@Repository
public interface CalendarioEscolarRepository extends Neo4jRepository<CalendarioEscolar, Long>{
    public Optional<CalendarioEscolar> findByAnoLetivo(int anoLetivo);

    @Query("MATCH (c:CalendarioEscolar {anoLetivo:{0}}) RETURN c.calendario")
    public String ListaRotulos(int anoLetivo);
}