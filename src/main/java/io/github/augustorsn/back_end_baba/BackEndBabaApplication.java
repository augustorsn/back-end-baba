package io.github.augustorsn.back_end_baba;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import io.github.augustorsn.back_end_baba.domain.Pedido;
import io.github.augustorsn.back_end_baba.repository.PedidoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import io.github.augustorsn.back_end_baba.domain.Cliente;
import io.github.augustorsn.back_end_baba.repository.ClientesJpa;

import org.springframework.web.bind.annotation.GetMapping;


// @ComponentScan(basePackages = { "io.github.augustorsn.back_end_baba.repository",
// 		"io.github.augustorsn.back_end_baba.service" })
// @RestController
@SpringBootApplication
public class BackEndBabaApplication {

//	@Bean
//	public CommandLineRunner init(@Autowired ClientesJpa clientes, @Autowired PedidoJpa pedidos){
//		return args -> {
//			System.out.println("Salvando Clientes");
//			Cliente fulano = new Cliente("Fulano");
//			clientes.save(fulano);
//			Pedido p = new Pedido();
//			p.setCliente(fulano);
//			p.setDataPedido(LocalDate.now());
//			p.setTotal(BigDecimal.valueOf(150));
//			pedidos.save(p);
////			Cliente result = clientes.findClienteFetchPedidos(fulano.getId());
////			System.out.println("Clientes"+ result);
//
//			pedidos.findByCliente(fulano).forEach(System.out::println);
//			System.out.println("asdads");
//
//
//		};
//	}
	public static void main(String[] args) {
		SpringApplication.run(BackEndBabaApplication.class, args);
	}

}
