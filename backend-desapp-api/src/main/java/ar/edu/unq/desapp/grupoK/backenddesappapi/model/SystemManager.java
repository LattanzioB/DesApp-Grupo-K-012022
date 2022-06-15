package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.ArrayList;


public class SystemManager {
    
    private ArrayList<ModelUser> users;

    public SystemManager(){
        this.users = new ArrayList<ModelUser>();
    }

    public void addUser(ModelUser user){
        this.users.add(user);
    }

    public ArrayList<ModelUser> getUsers(){
        return this.users;
    }
}
