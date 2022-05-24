package my.apitests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import my.requests.*;
import my.responses.AuthResponses.*;
import my.utils.LogListener;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static specification.Specs.*;

@Listeners(LogListener.class)
public class AuthActions {

    @Step("Positive registration")
    @Severity(SeverityLevel.NORMAL)
    @Description("This is desc for \"Positive registration\" APITest.")
    public void registerSuccess() {
                var requestBody = new RegisterSuccessRequest();
                var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/register")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(200)
                    .extract().as(RegisterSuccessResponse.class);
            assertThat(response.getToken(), equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("This is desc for \"Negative registration\" APITest.")
    @Step("Negative registration")
    public void registerUnsuccess() {
        var requestBody = new RegisterSuccessRequest();
        requestBody.setPassword(null);
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/register")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(400)
                    .extract().as(RegisterUnsuccessResponse.class);
        assertThat(response.getError(), equalTo("Missing password"));
    }

    @Severity(SeverityLevel.MINOR)
    @Description("This is desc for \"Positive login\" APITest.")
    @Step("Positive login")
    public void loginSuccess() {
        var requestBody = new LoginSuccessRequest();
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/login")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(200)
                    .extract().as(LoginSuccessResponse.class);
        assertThat(response.getToken(), equalTo("QpwL5tke4Pnpja7X4"));
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("This is desc for \"Negative login\" APITest.")
    @Step("Negative login")
    public void loginUnsuccess() {
        var requestBody = new LoginSuccessRequest();
        requestBody.setPassword(null);
        var response =
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/login")
                    .body(requestBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(400)
                    .extract().as(LoginUnsuccessResponse.class);
        assertThat(response.getError(), equalTo("Missing password"));
    }




}
