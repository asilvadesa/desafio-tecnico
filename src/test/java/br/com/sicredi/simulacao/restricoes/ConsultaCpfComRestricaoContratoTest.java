package br.com.sicredi.simulacao.restricoes;

import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static br.com.sicredi.simulacao.service.SimulacaoCreditoService.consultaCpfSeTemRestricao;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ConsultaCpfComRestricaoContratoTest {

    private String path = "/api/v1/restricoes/";

    @ParameterizedTest
    @CsvSource("84809766080")
    void validaContratoConsultaCpfComRestricao(String cpf){
        Response response = consultaCpfSeTemRestricao(cpf);
        response.then().body(matchesJsonSchemaInClasspath("schemas/getConsultaCpfRestricao.json"));
    }
}
