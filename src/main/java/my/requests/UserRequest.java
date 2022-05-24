package my.requests;

import lombok.Data;

@Data
public class UserRequest {
    //@SerializedName("name")
    private String name = "Evgeny";

    //@SerializedName("job")
    private String job = "QA";

}
