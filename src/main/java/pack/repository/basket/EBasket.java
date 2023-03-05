package pack.repository.basket;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name = "\"basket\"")

public class EBasket implements Serializable{
    @Id
    @Column(name = "\"id\"")
    private int id;
    @Column(name = "\"name\"")
    private String name;
    @Column(name = "\"parametrs\"")
    private String parametrs;
    @Column(name = "\"total\"")
    private int total;

    @Column(name = "\"userLogin\"")
    private String userLogin;
  
      public void setTotal(int total) {
          this.total = total;
      }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public int getTotal() {
          return total;
      }
  
      public int getId() {
          return id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getParametrs() {
          return parametrs;
      }
  
      public void setParametrs(String parametrs) {
          this.parametrs = parametrs;
      }
  
      public void setId(int id) {
          this.id = id;
      }
}
