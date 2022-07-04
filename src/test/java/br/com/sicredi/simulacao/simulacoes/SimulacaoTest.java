package br.com.sicredi.simulacao.simulacoes;

import br.com.sicredi.simulacao.domain.Simulacao;
import com.github.javafaker.Faker;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static br.com.sicredi.simulacao.factory.SimulacaoFactory.*;
import static br.com.sicredi.simulacao.specs.request.SimulacaoCreditoRequestSpec.simulacaoCreditoSpecs;
import static br.com.sicredi.simulacao.specs.response.SimulacaoCreditoResponseSpec.*;
import static br.com.sicredi.simulacao.specs.response.SimulacaoCreditoResponseSpec.validaBuscaSimulacaoAtravesCpfSpec;
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

    @Test
    void validaCasdastroSimulacaoComCpfJaExistente(){
        Simulacao simulacao = criaSimulacao();

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
            .body(simulacao)
        .post()
            .then()
        .spec(validaCadastroNovaSimulacaoSpec(simulacao));

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
            .body(simulacao)
        .post()
            .then()
        .spec(validaCadastroNovaSimulacaoComCpfJaExistente());

    }

    @Test
    void validaRetornoDeUmaSimulacaoAtracesDoCpf(){
        Simulacao simulacao = criaSimulacao();

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
            .body(simulacao)
        .post()
            .then()
            .spec(validaCadastroNovaSimulacaoSpec(simulacao));

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
        .get(simulacao.getCpf())
            .then()
            .spec(validaBuscaSimulacaoAtravesCpfSpec(simulacao));
    }

    @Test
    void validaRetornoDeUmaSimulacaoAtracesDoCpfInexistente(){
        Simulacao simulacao = criaSimulacao();
        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
        .get(simulacao.getCpf())
            .then().log().all()
       .spec(validaBuscaSimulacaoComCpfInexistente(simulacao.getCpf()));
    }

    @Test
    void validaAtualizaSimulacaoExistenteAtravesDoCpf(){
        Simulacao simulacao = criaSimulacao();
        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
            .body(simulacao)
        .post()
            .then()
            .spec(validaCadastroNovaSimulacaoSpec(simulacao));

        Faker faker = new Faker();
        String nome = faker.name().fullName();
        simulacao.setNome(nome);

        given()
            .spec(simulacaoCreditoSpecs())
                .basePath(basePah)
                .body(simulacao)
            .put(simulacao.getCpf())
            .then()
                .spec(validaBuscaSimulacaoAtravesCpfSpec(simulacao));

    }

    @Test
    void validaAtualizaSimulacaoQueNaoExisteNaBaseAtravesDoCpf(){
        Simulacao simulacao = criaSimulacao();

        given()
            .spec(simulacaoCreditoSpecs())
            .basePath(basePah)
            .body(simulacao)
        .put(simulacao.getCpf())
        .then()
            .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/cpfComRestricao.csv")
    void validaAtualizaSimulacaoAtravesCpfComRestricao(String cpf){
        Simulacao simulacao = criaSimulacao();
        simulacao.setCpf(cpf);

        given()
                .spec(simulacaoCreditoSpecs())
                .basePath(basePah)
                .body(simulacao)
                .put(cpf)
                .then()
                .spec(validaAtualizaCpfComRestricao(cpf));
    }

}
