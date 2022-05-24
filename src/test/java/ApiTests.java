import my.apitests.AuthActions;
import my.apitests.UserActions;
import org.testng.annotations.Test;


public class ApiTests {

    @Test
    public void AuthTests() {
        var test = new AuthActions();
        test.loginSuccess();
        test.loginUnsuccess();
        test.registerSuccess();
        test.registerUnsuccess();
    }

    @Test
    public void UserTests() {
        var test = new UserActions();
        test.get();
        test.create();
        test.patch();
        test.put();
        test.delete();
        test.userNotFound();
    }
        //SECOND

}
