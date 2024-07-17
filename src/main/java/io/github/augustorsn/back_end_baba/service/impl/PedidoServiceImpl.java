package io.github.augustorsn.back_end_baba.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.augustorsn.back_end_baba.domain.Cliente;
import io.github.augustorsn.back_end_baba.domain.ItemPedido;
import io.github.augustorsn.back_end_baba.domain.Pedido;
import io.github.augustorsn.back_end_baba.domain.Produto;
import io.github.augustorsn.back_end_baba.enums.StatusPedido;
import io.github.augustorsn.back_end_baba.exception.PedidoNaoEncontradoException;
import io.github.augustorsn.back_end_baba.exception.RegraNegocioException;
import io.github.augustorsn.back_end_baba.repository.ClientesJpa;
import io.github.augustorsn.back_end_baba.repository.ItemPedidoJpa;
import io.github.augustorsn.back_end_baba.repository.PedidoJpa;
import io.github.augustorsn.back_end_baba.repository.ProdutoJpa;
import io.github.augustorsn.back_end_baba.rest.dto.ItensPedidoDTO;
import io.github.augustorsn.back_end_baba.rest.dto.PedidoDTO;
import io.github.augustorsn.back_end_baba.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoJpa pedidoRepository;
    private final ItemPedidoJpa itemPedidoRepository;
    private final ClientesJpa clienteRepository;
    private final ProdutoJpa produtoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedido) {

        Cliente c = clienteRepository.findById(pedido.getCliente())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado " + pedido.getCliente()));

        Pedido p = new Pedido();
        p.setCliente(c);
        p.setStatus(StatusPedido.REALIZADO);
        p.setTotal(pedido.getTotal());
        p.setDataPedido(LocalDate.now());
        List<ItemPedido> ip = this.converterItens(p, pedido.getItens());
        pedidoRepository.save(p);
        itemPedidoRepository.saveAll(ip);
        p.setItemsPedido(ip);
        return p;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItensPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Lista de itens do pedido vazia");
        }

        return itens.stream()
                .map(dto -> {
                    Produto produto = produtoRepository.findById(dto.getProduto())
                            .orElseThrow(() -> new RegraNegocioException(
                                    "Produto não encontrado com o código: " + dto.getProduto()));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido; // Retorna o ItemPedido criado
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

   
    @Override
    public void atualizarPedido(Integer id, StatusPedido statusNovo) {
       pedidoRepository.findById(id)
                .map(p -> {
                    p.setStatus(statusNovo);
                    pedidoRepository.save(p);
                    return p;
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

       
    }

}
