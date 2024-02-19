package com.sitracker.sigametracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spirits")
public class Spirit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String pathname;

    @Column(nullable = false)
    private String image;

    /** Getters **/
    public Long getId() { return id; }

    public String getName() { return name; }

    public String getImage() { return image; }

    /** Setters **/
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setImage(String image) { this.image = image; }
}
