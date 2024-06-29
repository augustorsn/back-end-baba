package io.github.augustorsn.back_end_baba.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.augustorsn.back_end_baba.domain.Cliente;

@Repository
public class ClientesJpa {
    
    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String UPDATE = "UPDATE cliente SET nome = ? WHERE id = ?";
    private static String DELETE = "DELETE FROM cliente WHERE id = ";

    private static final String FIND_ALL = "SELECT id, nome FROM cliente";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[] { cliente.getNome() });
        return cliente;
    }

    public Cliente update(Cliente cliente) {
        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getId());
        return cliente;
    }

    public Cliente delete(Cliente cliente) {
        jdbcTemplate.update(DELETE, cliente.getId());
        return cliente;
    }

    public List<Cliente> obterPorId(Integer id) {
        return jdbcTemplate.query(FIND_ALL.concat(" where id = ?"), new PreparedStatementSetter() {
            @Override
            public void setValues(@SuppressWarnings("null") PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        }, obterClienteMapper());
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(FIND_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @SuppressWarnings("null")
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(rs.getInt("id"), rs.getString("nome"));
            }
        };

    }

}
