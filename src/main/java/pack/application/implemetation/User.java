package pack.application.implemetation;

import pack.application.dto.Token;
import pack.application.api.in.IUser;
import pack.application.api.out.IUsersRep;

import java.util.Objects;
public class User implements IUser {
    private Token token = new Token();

    IUsersRep repository;
  
    @Override
    public void injectRepository(IUsersRep repository) {
      this.repository = repository;
    }
    public class answer {
        public String token;
        public String role;
    }
    public answer authorization(String email, String password) {
       answer a = new answer();
       a.role=repository.selectUser(email, password);
       a.token =token.getTokenKey(a.role);
          return a;
    }
    @Override
    public boolean authentication(String hash) {
      if(Objects.equals(token.validate(hash), "user")){
          return true;
      }
        return Objects.equals(token.validate(hash), "admin");
    }

    public String check(String hash){
        return token.validate(hash);
    }
}
