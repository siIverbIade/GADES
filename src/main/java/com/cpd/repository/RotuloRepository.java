package com.cpd.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.cpd.entity.nodes.RotuloCalendario;
import com.cpd.model.RotuloCalendarioModel;

@Repository
public interface RotuloRepository extends Neo4jRepository<RotuloCalendario, Long> {
    public RotuloCalendario findByNome(String nome);

    @Query("MATCH (c:CalendarioEscolar {anoLetivo: {0}}) WITH c UNWIND c.calendario as cal MATCH (r:RotuloCalendario) WHERE Id(r)=cal RETURN r.nome as nome, r.temAula as temAula, r.simbolo as simbolo, r.descricao as descricao, r.foreColor as foreColor, r.backColor as backColor")
    public List<RotuloCalendarioModel> rotulosAnoLetivo(int anoLetivo);
    
    public List<RotuloCalendario> findByGlobal(boolean global);

}