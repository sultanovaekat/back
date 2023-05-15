package pack.infrastructure.repository.order;

import java.io.Serializable;
import jakarta.persistence.*;


@Entity
@Table(name = "\"orders\"")

public class EOrder implements Serializable{
    @Id
    @Column(name = "\"user\"")
    private String user;
    @Column(name = "\"id_product\"")
    private int id;
    @Column(name = "\"name_product\"")
    private String name;
    @Column(name = "\"status\"")
    private boolean status;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
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

  
      public void setId(int id) {
          this.id = id;
      }
}
