package io.github.augustorsn.back_end_baba.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.augustorsn.back_end_baba.domain.Pedido;
import io.github.augustorsn.back_end_baba.exception.RegraNegocioException;
import io.github.augustorsn.back_end_baba.repository.PedidoJpa;
import io.github.augustorsn.back_end_baba.rest.dto.PedidoDTO;
import io.github.augustorsn.back_end_baba.service.impl.PedidoServiceImpl;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoServiceImpl service;
    private PedidoJpa pedidoRepository;

    public PedidoController(PedidoServiceImpl service, PedidoJpa pedidoRepository) {
        this.service = service;
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedido) {

        Pedido pedidoNew = service.salvar(pedido);
        return pedidoNew.getId();

    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido getPedido(@PathVariable Integer id) {
        java.util.Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(pedido.isPresent()){
            return pedido.get();
        }
        throw new RegraNegocioException("pedido com o código "+id+" não encontrado");       
    } 

}
