package br.com.sicredi.simulacao.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SimulacaoCreditoSpec {

    public static RequestSpecification simulacaoCreditoSpecs(){
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .build();
    }
}
