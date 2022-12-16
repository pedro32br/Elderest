package br.com.pucminas.elderest.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {
    @Email
    private String username;
    @Size(max = 20, min = 6, message = "Senha precisa ter valor maximo de 20 characteres e minimo 6")
    private String password;
}
