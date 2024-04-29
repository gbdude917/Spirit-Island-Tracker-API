package com.sitracker.sigametracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "authority_id")
    private Long id;

    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() { return id; }

    public String getAuthority() { return authority; }

    public User getUser() { return user; }

    public void setId(Long id) { this.id = id; }

    public void setAuthority(String authority) { this.authority = authority; }

    public void setUser(User user) { this.user = user; }
}
