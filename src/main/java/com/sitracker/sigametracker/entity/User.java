package com.sitracker.sigametracker.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
//    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private Date registrationDate;

    public User() {

    }

    public User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
    }

    /** Getters **/
    public Long getId() { return id; }

    public String getEmail() { return email; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public Date getRegistrationDate() { return registrationDate; }

    /** Setters **/
    public void setId(Long id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    public static class Builder {
        private Long id;
        private String email;
        private String username;
        private String password;

        public Builder() {

        }

        public static Builder builder()
        {
            return new Builder();
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
