package my.pojos;
import lombok.Data;
import java.util.List;

@Data
public class UsersListPojo {

    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<UserPojo.UserPojoData> data;
    private UserPojo.SupportPojo support;
}
