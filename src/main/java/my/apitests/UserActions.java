package my.apitests;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import my.pojos.UserPojo;
import my.requests.UserRequest;
import my.responses.UserResponses.PatchUserResponse;
import my.responses.UserResponses.PutUserResponse;
import my.utils.LogListener;
import org.testng.annotations.Listeners;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;
import static specification.Specs.*;

@Listeners(LogListener.class)
public class UserActions implements CRUD{

    @Step("Create user")
    public void create() {
        UserRequest testBody = new UserRequest();
                given()
                    .spec(defaultRequestSpec)
                    .basePath("/users/")
                    .body(testBody)
                .when()
                    .post()
                .then()
                    .spec(defaultResponseSpec)
                    .statusCode(201);
    }

    @Step("Get user")
    public void get() {
        final int  ID = 2;
            var response = given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
            .when()
                .get()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(UserPojo.class);
            assertThat(response.getData().getFirstName(), equalTo("Janet"));
            assertThat(response.getData().getLastName(), equalTo("Weaver"));
            assertThat(response.getData().getEmail(), equalTo("janet.weaver@reqres.in"));
            assertThat(response.getData().getId(), equalTo(2));
    }

    @Step("Delete user")
    public void delete() {
        final int  ID = 1;
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
            .when()
                .delete()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(204);
    }

    @Step("Put user")
    public void put() {
        var testBody = new UserRequest();
                testBody.setName("Johny");
                testBody.setJob("QA2");
        final int  ID = 2;
            var putUser = given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
                .body(new Gson().toJson(testBody))
            .when()
                .put()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(PutUserResponse.class);
        assertThat(putUser.getName(), equalTo("Johny"));
        assertThat(putUser.getJob(), equalTo("QA2"));
    }

    @Step("Patch user")
    public void patch() {
        var testBody = new UserRequest();
            testBody.setName("Johny");
            testBody.setJob("QA2");
        final int  ID = 2;
            var response =
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
                .body(new Gson().toJson(testBody))
            .when()
                .patch()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(200)
                .extract().as(PatchUserResponse.class);
        assertThat(response.getName(), equalTo("Johny"));
        assertThat(response.getJob(), equalTo("QA2"));
    }

    @Step("User nof found")
    public void userNotFound() {
        final int  ID = 23;
        var response =
            given()
                .spec(defaultRequestSpec)
                .basePath("/users/" + ID)
            .when()
                .get()
            .then()
                .spec(defaultResponseSpec)
                .statusCode(404);
    }

}
