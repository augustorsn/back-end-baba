package io.github.augustorsn.back_end_baba.repository;

import io.github.augustorsn.back_end_baba.domain.Pedido;
import io.github.augustorsn.back_end_baba.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoJpa extends JpaRepository<Produto, Integer> {
}
