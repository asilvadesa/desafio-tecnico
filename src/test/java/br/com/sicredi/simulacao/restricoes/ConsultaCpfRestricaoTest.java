package br.com.sicredi.simulacao.restricoes;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.text.MessageFormat;

import static br.com.sicredi.simulacao.service.SimulacaoCreditoService.consultaCpfSeTemRestricao;
import static org.hamcrest.Matchers.is;

@DisplayName("Consulta Status do CPF")
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
}
