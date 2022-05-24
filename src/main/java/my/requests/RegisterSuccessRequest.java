package my.requests;

import lombok.Data;

@Data
public class RegisterSuccessRequest {

    private String email = "eve.holt@reqres.in";
    private String password = "pistol";
}
