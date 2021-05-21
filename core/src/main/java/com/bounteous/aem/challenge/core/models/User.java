package com.bounteous.aem.challenge.core.models;

import com.bounteous.aem.challenge.core.models.properties.DateOfBirth;
import com.bounteous.aem.challenge.core.models.properties.Id;
import com.bounteous.aem.challenge.core.models.properties.Location;
import com.bounteous.aem.challenge.core.models.properties.Login;
import com.bounteous.aem.challenge.core.models.properties.Name;
import com.bounteous.aem.challenge.core.models.properties.Picture;
import com.bounteous.aem.challenge.core.models.properties.RegistrationData;

public class User {

    public User() {}

    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private DateOfBirth dob;
    private RegistrationData registered;
    private String phone;
    private String cell;
    private Id id;
    private Picture picture;
    private String nat;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public DateOfBirth getDob() {
        return dob;
    }

    public void setDob(DateOfBirth dob) {
        this.dob = dob;
    }

    public RegistrationData getRegistered() {
        return registered;
    }

    public void setRegistered(RegistrationData registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }
}
