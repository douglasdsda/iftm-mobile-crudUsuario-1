package com.example.crudusuario.dto;



import java.util.HashMap;
import java.util.Map;

public class DtoUsers {

private String email;
private Integer id;
private String name;
private String password;
private String phone;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DtoUsers(String email, String name, String password, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
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

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}