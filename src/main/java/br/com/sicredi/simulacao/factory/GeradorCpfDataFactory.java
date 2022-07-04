package br.com.sicredi.simulacao.factory;

import com.github.javafaker.Faker;

public class GeradorCpfDataFactory {
    public static String geradorCpf(){
        Faker faker = new Faker();
        String digits = faker.number().digits(11);
        return digits;
    }
}
