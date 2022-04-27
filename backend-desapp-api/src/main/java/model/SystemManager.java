package model;

import java.util.ArrayList;


public class SystemManager {
    
    private ArrayList<User> users;

    public SystemManager(){
        this.users = new ArrayList<User>();
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public ArrayList<User> getUsers(){
        return this.users;
    }
}
