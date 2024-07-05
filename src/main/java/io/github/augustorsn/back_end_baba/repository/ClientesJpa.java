package io.github.augustorsn.back_end_baba.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.augustorsn.back_end_baba.domain.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


public interface ClientesJpa  extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome);
    boolean existsByNome(String nome);

    @Query(value="select c from Cliente c where c.nome like :nome")
    List<Cliente> encontratPorNome(@Param("nome") String nome);


    @Query(value="select * from Cliente c where c.nome like '%:nome%'",nativeQuery = true)
    List<Cliente> encontrarPorNomeNativeSql(@Param("nome") String nome);
}
