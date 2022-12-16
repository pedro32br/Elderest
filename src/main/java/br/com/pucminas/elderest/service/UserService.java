package br.com.pucminas.elderest.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.pucminas.elderest.model.Usuario;
import br.com.pucminas.elderest.model.UsuarioDto;

public interface UserService extends UserDetailsService {

    Usuario save(UsuarioDto usuarioDto);

    List<Usuario> getAll();
}
