package br.com.sicredi.simulacao.specs.response;

import br.com.sicredi.simulacao.domain.Simulacao;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.text.MessageFormat;

import static org.hamcrest.Matchers.*;

public class SimulacaoCreditoResponseSpec {
    public static ResponseSpecification validaConsultaTodasSimulacoesSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectBody("id.size", is(notNullValue()))
                .expectBody("id.size", is(greaterThan(1)))
                .build();
    }

    public static ResponseSpecification validaCadastroNovaSimulacaoSpec(Simulacao simulacao){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_CREATED)
                .expectBody("nome", is(simulacao.getNome()))
                .expectBody("cpf", is(simulacao.getCpf()))
                .expectBody("email",is(simulacao.getEmail()))
                .expectBody("valor", is(simulacao.getValor()))
                .expectBody("parcelas",is(simulacao.getParcelas()))
                .expectBody("seguro", is(simulacao.getSeguro()))
                .build();
    }

    public static ResponseSpecification validaBuscaSimulacaoAtravesCpfSpec(Simulacao simulacao){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectBody("nome", is(simulacao.getNome()))
                .expectBody("cpf", is(simulacao.getCpf()))
                .expectBody("email",is(simulacao.getEmail()))
                .expectBody("valor", is(simulacao.getValor()))
                .expectBody("parcelas",is(simulacao.getParcelas()))
                .expectBody("seguro", is(simulacao.getSeguro()))
                .build();
    }

    public static ResponseSpecification validaCadastroNovaSimulacaoSemInformacaoDeParcelaSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody("erros.parcelas", is(notNullValue()))
                .expectBody("erros.parcelas", is("Parcelas não pode ser vazio"))
                .build();
    }

    public static ResponseSpecification validaCadastroNovaSimulacaoSemInformacaoDeParcelaEValorSpec(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)
                .expectBody("erros.parcelas", is(notNullValue()))
                .expectBody("erros.parcelas", is("Parcelas não pode ser vazio"))
                .expectBody("erros.valor", is("Valor não pode ser vazio"))
                .build();
    }
    public static ResponseSpecification validaCadastroNovaSimulacaoComCpfJaExistente(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_CONFLICT)
                .expectBody("mensagem", is("CPF já existente"))
                .build();
    }

    public static ResponseSpecification validaBuscaSimulacaoComCpfInexistente(String cpf){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectBody("mensagem", is(MessageFormat.format("O CPF {0} possui restrição", cpf)))
                .build();
    }

    public static ResponseSpecification validaAtualizaCpfComRestricao(String cpf){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_CONFLICT)
                .expectBody("mensagem", is(MessageFormat.format("O CPF {0} possui restrição", cpf)))
                .build();
    }

}
