package pack.application.dto;

public class Delivery {
    private int id;
    private String name;
    private String user;
    private boolean status;

    public Delivery() {

    }
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
