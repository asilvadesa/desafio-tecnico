package br.com.sicredi.simulacao.service;

import io.restassured.response.Response;

import static br.com.sicredi.simulacao.specs.request.SimulacaoCreditoRequestSpec.simulacaoCreditoSpecs;
import static io.restassured.RestAssured.given;


public class SimulacaoCreditoService {
    public static Response consultaCpfSeTemRestricao(String cpf){
        return given()
                .spec(simulacaoCreditoSpecs())
                .basePath("/api/v1/restricoes/")
                .get(cpf)
                .andReturn();
    }
}
