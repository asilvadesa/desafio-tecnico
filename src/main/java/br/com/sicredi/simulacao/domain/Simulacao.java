package br.com.sicredi.simulacao.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Simulacao {
    private String nome;
    private String cpf;
    private String email;
    private Integer valor;
    private Integer parcelas;
    private Boolean seguro;
}
