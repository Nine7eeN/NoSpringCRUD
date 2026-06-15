package entities;

import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String email;
    private Address address;

    public User (){}

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = null;
    }

    public User(Integer id, String username, String email, Address address) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {return address;}

    public void setAddress(Address address) {this.address = address;}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
