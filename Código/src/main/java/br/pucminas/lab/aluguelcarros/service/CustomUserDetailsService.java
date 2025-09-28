package br.pucminas.lab.aluguelcarros.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.pucminas.lab.aluguelcarros.model.Agente;
import br.pucminas.lab.aluguelcarros.model.Cliente;
import br.pucminas.lab.aluguelcarros.repository.AgenteRepository;
import br.pucminas.lab.aluguelcarros.repository.ClienteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgenteRepository agenteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(email);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE")); // Define o perfil
            return new User(cliente.getEmail(), cliente.getSenha(), authorities);
        }

        Optional<Agente> agenteOpt = agenteRepository.findByEmail(email);
        if (agenteOpt.isPresent()) {
            Agente agente = agenteOpt.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_AGENTE")); // Define o perfil
            return new User(agente.getEmail(), agente.getSenha(), authorities);
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
    }
}