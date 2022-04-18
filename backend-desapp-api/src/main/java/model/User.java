package model;

public class User {
    private String name;
    private String surname;
    private String email;
    private String adress;
    private String password;
    private Integer cvu;
    private Integer wallet;

    public User(String name, String surname, String email, String adress, String password, Integer cvu, Integer wallet){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.adress = adress;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
    }
}
