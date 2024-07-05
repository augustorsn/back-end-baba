package io.github.augustorsn.back_end_baba.repository;

import io.github.augustorsn.back_end_baba.domain.ItemPedido;
import io.github.augustorsn.back_end_baba.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoJpa extends JpaRepository<ItemPedido, Integer> {
}
