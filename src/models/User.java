package models;

public class User {
    private String userName;
    private final int userId;
    private String password;


//GETTER
    public User(String userName, int userId,String pass) {
        this.userName = userName;
        this.userId = userId;
        this.password=pass;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getPassword() {
        return password;
    }



    public int getUserId(){
        return this.userId;
    }


    //SETTERS
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
