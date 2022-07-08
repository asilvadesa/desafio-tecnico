package br.com.sicredi.simulacao.simulacoes;

import br.com.sicredi.simulacao.domain.Simulacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static br.com.sicredi.simulacao.factory.SimulacaoFactory.criaSimulacao;
import static br.com.sicredi.simulacao.specs.request.SimulacaoCreditoRequestSpec.simulacaoCreditoSpecs;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Tag("contrato")
@DisplayName("Contrato: Simulacao")
public class SimulacaoContratoTest {

    private String path = "/api/v1/simulacoes/";

    @Test
    @DisplayName("Valida Contrato Busca de Uma Soluacao Atraves do CPF")
    void validaContratoBuscaUmaSimulacaoComCpf(){
        Simulacao simulacao = criaSimulacao();

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(path)
            .body(simulacao)
        .when()
            .post();

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(path)
            .body(simulacao)
        .when()
            .get(simulacao.getCpf())
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/getBuscaSimulacaoAtravesCpf.json"));
    }

    @Test
    @DisplayName("Valida Contrato Busca de Todas Simulacoes Existentes")
    void validaContratoTodasSimulacoesExistentes(){
        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(path)
        .when()
            .get()
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/getBuscaTodasSimulacoesExistentes.json"));
    }

    @Test
    @DisplayName("Valida Contrato Cadastro Nova Simulacao")
    void validaCadastroNovaSimulacao(){
        Simulacao simulacao = criaSimulacao();

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(path)
            .body(simulacao)
        .when()
            .post()
        .then()
            .body(matchesJsonSchemaInClasspath("schemas/postCadastroNovaSimulacao.json"));
    }
}
