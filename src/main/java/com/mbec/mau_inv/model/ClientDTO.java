package com.mbec.mau_inv.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ClientDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String clientName;

    @Size(max = 255)
    private String address;

    @Size(max = 255)
    private String phoneNumber;

    @Size(max = 255)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(final String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}
