package pack.model;

import pack.model.api.dto.Token;
import pack.model.api.in.IUserModel;
import pack.model.api.out.IUsersRep;

import java.util.Objects;


public class UserModel implements IUserModel{
    private Token token = new Token();

    IUsersRep repository;
  
    @Override
    public void injectRepository(IUsersRep repository) {
      this.repository = repository;
    }

    public String authorization(String email, String password) {
      String role = repository.selectUser(email, password);
      if(role!="not found"){
        return token.getTokenKey(role);}
      else return "nothash";
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
