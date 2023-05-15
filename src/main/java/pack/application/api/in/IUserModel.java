package pack.application.api.in;
import pack.application.UserModel;
import pack.application.api.out.IUsersRep;

public interface IUserModel {
    boolean authentication(String hash);
    UserModel.answer authorization(String email, String password);
    void injectRepository(IUsersRep repository);
    String check(String hash);
}
