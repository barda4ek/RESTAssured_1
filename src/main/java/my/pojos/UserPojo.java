package my.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserPojo {

    private UserPojoData data;
    private SupportPojo support;

    @Data
    public static class UserPojoData {
        private Integer id;
        private String avatar;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String email;

    }

    @Data
    public static class SupportPojo {
        private String url;
        private String text;
    }

}
