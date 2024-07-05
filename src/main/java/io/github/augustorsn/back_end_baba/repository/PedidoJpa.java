package io.github.augustorsn.back_end_baba.repository;

import io.github.augustorsn.back_end_baba.domain.Cliente;
import io.github.augustorsn.back_end_baba.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpa extends JpaRepository<Pedido, Integer> {
}
