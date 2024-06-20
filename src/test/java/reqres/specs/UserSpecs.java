package reqres.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static reqres.helpers.CustomAllureListener.withCustomTemplates;

public class UserSpecs {

    public static RequestSpecification requestSpec = with()
            .log().all()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON);

    public static ResponseSpecification responseSpecOkStatus200 = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpecOkStatus201 = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification responseSpecOkStatus204 = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(204)
            .build();

    public static ResponseSpecification responseSpecClientStatus400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification responseSpecClientStatus404 = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(404)
            .build();
}