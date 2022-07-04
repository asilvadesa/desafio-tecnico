package br.com.sicredi.simulacao.domain;

import lombok.*;

@Data
@Builder
public class Simulacao {
    private String nome;
    private String cpf;
    private String email;
    private Integer valor;
    private Integer parcelas;
    private Boolean seguro;
}
