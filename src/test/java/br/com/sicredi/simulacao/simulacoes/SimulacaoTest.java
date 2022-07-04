package br.com.sicredi.simulacao.simulacoes;

import br.com.sicredi.simulacao.domain.Simulacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.com.sicredi.simulacao.factory.SimulacaoFactory.*;
import static br.com.sicredi.simulacao.specs.request.SimulacaoCreditoRequestSpec.simulacaoCreditoSpecs;
import static br.com.sicredi.simulacao.specs.response.SimulacaoCreditoResponseSpec.*;
import static io.restassured.RestAssured.given;

public class SimulacaoTest {

    private String basePah = "/api/v1/simulacoes";

    @Test
    @DisplayName("Valida Consulta de Todas as Simulacoes Existentes")
    void validaConsultaTodasSimulacoesExistentes(){
        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
        .get()
            .then()
        .spec(validaConsultaTodasSimulacoesSpec());
    }

    @Test
    @DisplayName("Valida Cadastro de Nova Simulacao com Sucesso")
    void validaInserirNovaSimulacaoQuandoCadastradaComSucesso(){
        Simulacao simulacao = criaSimulacao();

        given()
            .spec(simulacaoCreditoSpecs())
                .basePath(basePah)
                .body(simulacao)
        .post()
                .then()
                .spec(validaCadastroNovaSimulacaoSpec(simulacao));
    }

    @Test
    @DisplayName("Valida Error ao Cadastrar Nova Simualacao sem Informacao de Parcela")
    void validaInserirNovaSimulacaoComFaltaDeInformacaoDaParcela(){
        Simulacao simulacao = criaSimulacaoComFaltaDeInformacaoDaParcela();
        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
            .body(simulacao)
        .post()
            .then()
        .spec(validaCadastroNovaSimulacaoSemInformacaoDeParcelaSpec());

    }

    @Test
    @DisplayName("Valida Error ao Cadastrar Nova Simualacao sem Informacao de Parcela e Valor")
    void validaInserirNovaSimulacaoComFaltaDeInformacoesDeParcelasEValor(){
        Simulacao simulacao = criaSimulacaoComFaltaDeInformacoes();
        given()
                .spec(simulacaoCreditoSpecs())
                .basePath(basePah)
                .body(simulacao)
                .post()
                .then()
                .spec(validaCadastroNovaSimulacaoSemInformacaoDeParcelaEValorSpec());
    }
}
