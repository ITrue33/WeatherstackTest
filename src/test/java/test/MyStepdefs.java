package test;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import specification.Specification;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {

    String accessKey = "23548b1fb4f6cf768cd275f4a5a7fbb1";
    Response response;

    @Дано("^Ссылка на ресурс (.*)")
    public void ссылкаНаРесурс(String url) {
        RestAssured.baseURI = url;
        /*RestAssured.requestSpecification = Specification.requestSpec(url);
        RestAssured.responseSpecification = Specification.responseSpec();*/
    }

    @Когда("^Отправлен запрос к несуществующему городу (.*)")
    public void отправленЗапросКНесуществующемуГороду(String city) {
        response = given().when().get("/current?access_key="+accessKey+"&query="+city);
    }

    @Тогда("Получен код ошибки {int}")
    public void полученКодОшибки(int code) {
        response.then().assertThat().body("error.code", equalTo(code));
    }



    @Когда("^Отправлен запрос к несуществующему ресурсу (.*)")
    public void отправленЗапросКНесуществующемуРесурсу(String resource) {
        response = given().when().get("/current?access_key="+accessKey);
    }

    @Когда("^Отправлен запрос к существующему городу без Access Key (.+)")
    public void отправленЗапросКСуществующемуГородуБезAccessKey(String city) {
        response = given().when().get("/current&query="+city);
    }

    @Когда("^Отправлен запрос к городу (.+) указав unit (.+)")
    public void отправленЗапросКГородуГородУказавUnitUnit(String city, String unit) {
        String params = String.join("&", "access_key=" + accessKey, "query=" + city, "units=" + unit);
        response = given().when().get("/current?" + params );
    }
}
