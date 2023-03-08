package sg.edu.nus.iss.app.day13workshop;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class User {
    
    @NotBlank(message="Name cannot be blank")
    @Size(min=3, max=64, message="Name must be within 3 to 64 characters")
    private String name;
    
    @NotBlank(message="Email cannot be blank")
    @Email(message="Invalid email")
    private String email;
    
    @NotBlank(message="Phone number cannot be blank")
    @Size(min=7, message="Phone number must contain at least 7 digits")
    private String phoneNumber;
    
    @Past(message="Date of birth must be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private LocalDate dateOfBirth;

    @Min(value=10, message="Age must be at least 10 years old")
    @Max(value=100, message="Age cannot be more than 100 years old")
    private int age;

    private String id;

    public User(
            String name,
            String email,
            String phoneNumber,
            LocalDate dateOfBirth,
            String id) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();;
        this.id = id;
    }

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();;
    }

    public int getAge() {
        return age;
    }
  
    // public int setAge(Date dateOfBirth) {
    //     return (int) ((new Date()).getTime() - dateOfBirth.getTime())/ (1000 * 60 * 60 * 24 * 365);
    // }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
