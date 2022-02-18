package siit.model;

public class User {
    private final int id;
    private final String name;
    private String email;
    private boolean isactive;

    public User(int id, String name, String email, boolean isactive){
        this.id = id;
        this.name = name;
        this.email = email;
        this.isactive = isactive;
    }

    public String getName(){
        return this.getName();
    }

    public String getEmail(){
        return this.email;
    }

    public int getId(){
        return this.id;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setIsactive(boolean isactive){
        this.isactive = isactive;
    }


}
