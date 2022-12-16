package br.com.pucminas.elderest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pucminas.elderest.model.Usuario;
import br.com.pucminas.elderest.model.UsuarioDto;
import br.com.pucminas.elderest.model.UsuarioPrincipal;
import br.com.pucminas.elderest.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder passwordEncoder;

    private final UsuarioRepository repository;

    @Override
    public Usuario save(final UsuarioDto usuarioDto) {

        final Usuario usuario = new Usuario(
                usuarioDto.getUsername(),
                passwordEncoder.encode(usuarioDto
                        .getPassword()));

        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        final Usuario user = repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return new UsuarioPrincipal(user);
    }

    @Override
    public List<Usuario> getAll() {

        return repository.findAll();
    }
}
