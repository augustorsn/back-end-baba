package io.github.augustorsn.back_end_baba.service;

import io.github.augustorsn.back_end_baba.domain.Pedido;
import io.github.augustorsn.back_end_baba.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO pedido);
       
}
