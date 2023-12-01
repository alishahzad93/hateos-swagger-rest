package com.aliments.hateosswaggerrest.user;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class OrkutUser {
    private Integer id;
    @Size(min = 2, message = "User Name should be at least 2 characters")
    private String userName;
    @Past(message = "Birth Date cannot be today or in Future")
    private LocalDate birthDate;

    public OrkutUser(Integer id, String userName, LocalDate birthDate) {
        this.id = id;
        this.userName = userName;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "OrkutUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
