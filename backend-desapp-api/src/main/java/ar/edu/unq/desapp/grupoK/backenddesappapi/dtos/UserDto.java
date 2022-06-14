package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    
    private Integer id;
    
    @NotEmpty
	@Size(min = 3,max = 30, message = "user name should have at least 3 characters and less than 30")
    private String name;
    @NotEmpty
	@Size(min = 3,max = 30, message = "user surname should have at least 3 characters and less than 30")
    private String surname;

	@NotEmpty
	@Email
    private String email;
    
    
    
    
    //Dirección, Obligatorio, Min:10, Max:30
    @NotEmpty
	@Size(min = 10,max = 30, message = "user adress should have at least 10 characters and less than 30")
    private String adress;

    //Contraseña: al menos 1 minuscula, 1 mayuscula, 1 carac especial y min 6
    //missing regex
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-_.*()<>+?¡¿!#$%&]).{8,30}$",
    message="La contraseña debe contener como mínimo 6 caracteres donde debe haber al menos 1 minuscula, 1 mayuscula y 1 caracter especial")// - Checks if the annotated string matches the regular expression regex considering the given flag match
    private String password;


    //CVU MercadoPago, Obligatorio (22 digitos)
    @Digits(integer=22, fraction=0)
    private Integer cvu;
    //Dirección Billetera de CriptoActivos, Obligatorio (8 dígitos)
    @Digits(integer=8, fraction=0)
    private Integer wallet;

    public UserDto(String name, String surname,String email,String adress,String password, Integer cvu,Integer wallet) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.adress = adress;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
    }

    public UserDto() {
		
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCvu() {
        return cvu;
    }

    public void setCvu(Integer cvu) {
        this.cvu = cvu;
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    
}

