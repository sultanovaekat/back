package pack.application.api.in;
import pack.application.implemetation.User;
import pack.application.api.out.IUsersRep;

public interface IUser {
    boolean authentication(String hash);
    User.answer authorization(String email, String password);
    void injectRepository(IUsersRep repository);
    String check(String hash);
}
