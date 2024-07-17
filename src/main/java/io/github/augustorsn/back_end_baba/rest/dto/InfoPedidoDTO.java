package io.github.augustorsn.back_end_baba.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPedidoDTO {
    private Integer codPedido;
    private String nomeCliente;
    private String dataPedido;
    private String cpf;
    private BigDecimal total;
    private List<InfoItensPedidoDTO> itens;
}
