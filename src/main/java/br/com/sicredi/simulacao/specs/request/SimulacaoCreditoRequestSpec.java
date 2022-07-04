package br.com.sicredi.simulacao.specs.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class SimulacaoCreditoRequestSpec {

    public static RequestSpecification simulacaoCreditoSpecs(){
        return new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setContentType(ContentType.JSON)
                .setPort(8080)
                .build();
    }
}
