package com.cpd.repository;

import java.util.List;
import java.util.Optional;
import com.cpd.entity.nodes.Base;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface AuditableRepository<T extends Base, ID extends Long> extends Neo4jRepository<T, ID> {
    @Override
    @Transactional(readOnly = true)
    @Query("MATCH (e:#{#entityName}) WHERE Id(e) IN ?1 and e.ativo = true RETURN e")
    Iterable<T> findAllById(Iterable<ID> ids);

    @Override
    @Transactional(readOnly = true)
    @Query("MATCH (e:#{#entityName}) WHERE Id(e) = ?1 and e.ativo = true RETURN e")
    Optional<T> findById(ID id);

    // Look up deleted entities
    @Query("MATCH (e:#{#entityName}) WHERE e.ativo = false RETURN e")
    @Transactional(readOnly = true)
    List<T> findInactive();

    @Override
    @Transactional(readOnly = true)
    @Query("MATCH (e:#{#entityName}) WHERE e.ativo = true RETURN count(e)")
    long count();

    @Override
    @Query("MATCH (e:#{#entityName}) set e.ativo = false WHERE Id(e) = ?1 RETURN count(e)")
    @Transactional
    void deleteById(Long id);

    @Override
    @Transactional
    default void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(entitiy -> deleteById(entitiy.getId()));
    }

    @Override
    @Query("update #{#entityName} e set e.isActive=false")
    @Transactional
    void deleteAll();
}