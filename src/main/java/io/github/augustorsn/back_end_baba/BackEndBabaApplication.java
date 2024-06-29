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
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {			
			clientes.salvar(new Cliente("Guto"));
			clientes.salvar(new Cliente("augusto"));
			
			List<Cliente> todos = clientes.obterTodos();
			todos.forEach(System.out::println);

			List<Cliente> todos2 = clientes.obterPorId(1);
		
			todos2.forEach(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(BackEndBabaApplication.class, args);
	}

}
