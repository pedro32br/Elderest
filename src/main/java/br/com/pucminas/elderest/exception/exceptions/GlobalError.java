package br.com.pucminas.elderest.exception.exceptions;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalError {
    private Integer status;
    private Map<String, String> errors;
}
