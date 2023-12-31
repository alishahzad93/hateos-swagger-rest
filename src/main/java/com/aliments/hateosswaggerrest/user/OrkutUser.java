package com.aliments.hateosswaggerrest.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
//@JsonIgnoreProperties("birthDate") // static filtering
@JsonFilter("OrkutUserFilter")
public class OrkutUser {
    private Integer id;
    @Size(min = 2, message = "User Name should be at least 2 characters")
    @JsonProperty("user_name")
    private String userName;
    @Past(message = "Birth Date cannot be today or in Future")
    //@JsonIgnore //static filtering
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
