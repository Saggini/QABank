import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiPixTest {

    @Test
    @DisplayName("Deve consultar API externa para validar chave PIX (Simulação)")
    public void validarConsultaDeClienteExterno() {

        // GIVEN: Apontamos para o JSONPlaceholder (Sem bloqueios de segurança)
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .log().all()

                // WHEN: Buscamos o cliente de ID número 2
                .when()
                .get("/users/2")

                // THEN: Validamos se o status é 200 e se o nome do cliente está correto
                .then()
                .log().all()
                .statusCode(200)
                // O cliente número 2 nesta API se chama "Ervin Howell"
                .body("name", equalTo("Ervin Howell"))
                .body("email", equalTo("Shanna@melissa.tv")); // Podemos validar vários campos!
    }
}