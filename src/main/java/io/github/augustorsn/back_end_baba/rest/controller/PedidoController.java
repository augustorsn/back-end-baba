package io.github.augustorsn.back_end_baba.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.augustorsn.back_end_baba.domain.Pedido;
import io.github.augustorsn.back_end_baba.rest.dto.PedidoDTO;
import io.github.augustorsn.back_end_baba.service.impl.PedidoServiceImpl;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoServiceImpl service;

    public PedidoController(PedidoServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedido) {

        Pedido pedidoNew = service.salvar(pedido);
        return pedidoNew.getId();

    }

}
