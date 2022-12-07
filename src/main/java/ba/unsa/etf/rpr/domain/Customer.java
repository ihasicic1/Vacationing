package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * class that holds customer info for my traveling agency
 * @author Ilhan Hasicic
 */

public class Customer {
    private int id;
    private String first_name;
    private String last_name;
    private char gender;
    private Integer phone_number;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && gender == customer.gender && Objects.equals(first_name, customer.first_name) && Objects.equals(last_name, customer.last_name) && Objects.equals(phone_number, customer.phone_number) && Objects.equals(email, customer.email) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, gender, phone_number, email, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", gender=" + gender +
                ", phone_number=" + phone_number +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
