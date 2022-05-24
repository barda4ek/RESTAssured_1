package my.requests;

import lombok.Data;

@Data
public class LoginSuccessRequest {

    private String email = "eve.holt@reqres.in";
    private String password = "cityslicka";

}
