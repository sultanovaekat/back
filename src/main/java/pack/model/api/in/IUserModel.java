package pack.model.api.in;
import pack.model.api.out.IUsersRep;

import java.security.Key;

public interface IUserModel {
    boolean authentication(String hash);
    public String authorization(String email, String password);
    void injectRepository(IUsersRep repository);
    String check(String hash);
}
