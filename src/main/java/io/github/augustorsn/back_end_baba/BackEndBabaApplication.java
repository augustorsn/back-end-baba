package io.github.augustorsn.back_end_baba;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import io.github.augustorsn.back_end_baba.domain.Cliente;
import io.github.augustorsn.back_end_baba.repository.Clientes;
import io.github.augustorsn.back_end_baba.repository.ClientesJpa;

import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@ComponentScan(basePackages = { "io.github.augustorsn.back_end_baba.repository",
		"io.github.augustorsn.back_end_baba.service" })
@RestController
public class BackEndBabaApplication {

	@GetMapping("/hello")
	public String helloWorld() {
		return "Neymar";
	}


	@Bean
	public CommandLineRunner init(@Autowired ClientesJpa clientes){
		return args -> {			
			clientes.save(new Cliente("Guto"));
			clientes.save(new Cliente("augusto"));

			List<Cliente> todos = clientes.findAll();
			todos.forEach(System.out::println);
			clientes.deleteById(2);
			List<Cliente> todos2 = clientes.findAll();
			todos2.forEach(System.out::println);
			List<Cliente> listaCliente = clientes.findByNomeLike("Guto");
			if(listaCliente.size() >=1){
				Cliente c = listaCliente.get(0);
				c.setNome("Feio");
				clientes.save(c);
				List<Cliente> todos3 = clientes.findAll();
				todos3.forEach(System.out::println);
				boolean existe = clientes.existsByNome("Feio");
				System.out.println("existe cliente feio = "+ existe);
			}
			

			
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BackEndBabaApplication.class, args);
	}

}
