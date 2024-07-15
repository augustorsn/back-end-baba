package io.github.augustorsn.back_end_baba.service.impl;

import org.springframework.stereotype.Service;

import io.github.augustorsn.back_end_baba.repository.PedidoJpa;
import io.github.augustorsn.back_end_baba.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    private PedidoJpa repository;

    public PedidoServiceImpl(PedidoJpa repository) {
        this.repository = repository;
    }

}
