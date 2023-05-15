package pack.infrastructure.repository.user;
import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "\"users\"")
public class EUser implements Serializable{

    @Id
    @Column(name = "\"login\"")
    private String login;
    @Column(name = "\"password\"")
    private String password;



    @Column(name = "\"role\"")
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return login;
    }

    public void setEmail(String email) {
        this.login = email;
    }
}