package my.responses.AuthResponses;

import lombok.Data;

@Data
public class LoginSuccessResponse {

    private Integer id;
    private String token;
}
