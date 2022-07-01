package br.com.sicredi.simulacao.restricoes;

import br.com.sicredi.simulacao.service.SimulacaoCreditoService;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Consulta Status do CPF")
public class ConsultaCpfRestricaoTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/cpfComRestricao.csv")
    @DisplayName("Consulta deve retornar CPF com restrição")
    void validaConsultaCpfComRestricaoTest(String cpf){
        Response response = SimulacaoCreditoService.consultaCpfSeTemRestricao(cpf);
        System.out.println(response.then().log().all());
    }
}
