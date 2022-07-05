package br.com.sicredi.simulacao.restricoes;

import br.com.sicredi.simulacao.factory.GeradorCpfDataFactory;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.MessageFormat;

import static br.com.sicredi.simulacao.factory.GeradorCpfDataFactory.geradorCpf;
import static br.com.sicredi.simulacao.service.SimulacaoCreditoService.consultaCpfSeTemRestricao;
import static org.hamcrest.Matchers.is;

@DisplayName("Consulta Status do CPF")
@Tag("funcional")
public class ConsultaCpfRestricaoTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/cpfComRestricao.csv")
    @DisplayName("Consulta deve retornar CPF com restrição")
    void validaConsultaCpfComRestricaoTest(String cpf){
        Response response = consultaCpfSeTemRestricao(cpf);
        response.then()
        .statusCode(HttpStatus.SC_OK)
        .body("mensagem", is(MessageFormat.format("O CPF {0} tem problema", cpf)));
    }

    @Test
    @MethodSource("br.com.sicredi.simulacao.factory.GeradorCpfDataFactory#geradorCpf")
    void validaConsultaCpfSemRestricao(){
        Response response = consultaCpfSeTemRestricao(geradorCpf());
        response.then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
