package br.pucminas.lab.aluguelcarros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.pucminas.lab.aluguelcarros.model.Agente;
import br.pucminas.lab.aluguelcarros.model.Automovel;
import br.pucminas.lab.aluguelcarros.model.Cliente;
import br.pucminas.lab.aluguelcarros.repository.AgenteRepository;
import br.pucminas.lab.aluguelcarros.repository.ClienteRepository;
import br.pucminas.lab.aluguelcarros.service.AutomovelService;

@SpringBootApplication
public class AluguelCarrosApplication {
    public static void main(String[] args) {
        SpringApplication.run(AluguelCarrosApplication.class, args);
    }

    @Bean
    CommandLineRunner run(AutomovelService automovelService, ClienteRepository clienteRepository, AgenteRepository agenteRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Adiciona carros de teste, se não existirem
            if (automovelService.buscarTodos().isEmpty()) {
                Automovel carro1 = new Automovel();
                carro1.setMatricula("12345678901");
                carro1.setMarca("Fiat");
                carro1.setModelo("Mobi");
                carro1.setAno(2023);
                carro1.setPlaca("ABC1D23");
                automovelService.salvar(carro1);

                Automovel carro2 = new Automovel();
                carro2.setMatricula("98765432109");
                carro2.setMarca("Hyundai");
                carro2.setModelo("HB20");
                carro2.setAno(2024);
                carro2.setPlaca("XYZ9H87");
                automovelService.salvar(carro2);
            }

            // Adiciona usuário CLIENTE de teste, se não existir
            if (clienteRepository.findByEmail("joao@cliente.com").isEmpty()) {
                Cliente cliente = new Cliente();
                cliente.setNome("Joao Silva");
                cliente.setEmail("joao@cliente.com");
                cliente.setSenha(passwordEncoder.encode("4321"));
                cliente.setCpf("111.111.111-11");
                cliente.setRg("MG-11.111.111");
                clienteRepository.save(cliente);
            }

            // Adiciona usuário AGENTE de teste, se não existir
            if (agenteRepository.findByEmail("admin@agente.com").isEmpty()) {
                Agente agente = new Agente();
                agente.setNomeEmpresa("Empresa de Aluguel Segura");
                agente.setEmail("admin@agente.com");
                agente.setSenha(passwordEncoder.encode("1234"));
                agente.setCnpj("00.000.000/0001-00");
                agente.setTipo(Agente.TipoAgente.EMPRESA); 
                agenteRepository.save(agente);
            }
        };
    }
}
