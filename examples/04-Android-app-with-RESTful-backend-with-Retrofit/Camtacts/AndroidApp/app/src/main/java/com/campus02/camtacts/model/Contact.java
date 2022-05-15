package com.campus02.camtacts.model;

public class Contact {

  private int id;
  private String firstName;
  private String lastName;
  private String phoneNumber;

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) { this.firstName = firstName; }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() { return (firstName + " " + lastName).trim(); }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() { return phoneNumber; }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
}
