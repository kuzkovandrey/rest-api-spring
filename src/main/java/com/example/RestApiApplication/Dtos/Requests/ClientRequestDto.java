package com.example.RestApiApplication.Dtos.Requests;

public class ClientRequestDto {
  private String email;

  private String name;

  ClientRequestDto() {}

  public ClientRequestDto(String email, String name) {
    this.email = email;
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }
}
