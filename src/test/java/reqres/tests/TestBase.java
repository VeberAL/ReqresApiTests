package reqres.tests;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import reqres.config.ApiConfig;

public class TestBase {
    private final static ApiConfig config = ConfigFactory.create(ApiConfig.class);

    @BeforeAll
    static void beforeAll() {

        RestAssured.baseURI = config.baseApiUri();
        RestAssured.basePath = config.basePath();
    }

}
