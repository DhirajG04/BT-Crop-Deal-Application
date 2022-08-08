package com.cda.crop.model;

public class AuthResponse {

	
	private String name;
    private String role;
    private boolean isValid;
    public AuthResponse(String name, String role, boolean isValid) {
        super();
        this.name = name;
        this.role = role;
        this.isValid = isValid;
    }
    public AuthResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public boolean isValid() {
        return isValid;
    }
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
