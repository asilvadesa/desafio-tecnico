# Instruções Básicas

#### Para execução do testes:


| Testes    | command   |
|-----------|-----------|
| Todos     | `mvn -PallTests test`        |
| Funcional | `mvn -Dtests=funcional test` |
| Contrato  | `mvn -Dtests=contrato test` |

***

#### Gerar relatório: 
- `mvn allure:serve` 
- `mvn allure:report`