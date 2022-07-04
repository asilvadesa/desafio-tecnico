package br.com.sicredi.simulacao.factory;

import br.com.sicredi.simulacao.domain.Simulacao;
import com.github.javafaker.Faker;

import static br.com.sicredi.simulacao.factory.GeradorCpfDataFactory.geradorCpf;

public class SimulacaoFactory {

    public static Simulacao criaSimulacao(){
        Faker faker = new Faker();
        return Simulacao.builder()
                .nome(faker.name().fullName())
                .cpf(geradorCpf())
                .email(faker.name().firstName().concat("@faker.com"))
                .valor(Integer.parseInt(faker.number().digits(4)))
                .parcelas(faker.number().numberBetween(1,10))
                .seguro(Boolean.parseBoolean(faker.bool().toString()))
                .build();
    }

    public static Simulacao criaSimulacaoComFaltaDeInformacaoDaParcela(){
        Faker faker = new Faker();
        return Simulacao.builder()
                .nome(faker.name().fullName())
                .cpf(geradorCpf())
                .email(faker.name().firstName().concat("@faker.com"))
                .valor(Integer.parseInt(faker.number().digits(4)))
                .seguro(Boolean.parseBoolean(faker.bool().toString()))
                .build();
    }

    public static Simulacao criaSimulacaoComFaltaDeInformacoes(){
        Faker faker = new Faker();
        return Simulacao.builder()
                .nome(faker.name().fullName())
                .cpf(geradorCpf())
                .email(faker.name().firstName().concat("@faker.com"))
                .seguro(Boolean.parseBoolean(faker.bool().toString()))
                .build();
    }
}
