package com.sitracker.sigametracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "spirits")
public class Spirit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spirit_id")
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

    public String getPathname() { return pathname; }

    public String getImage() { return image; }

    /** Setters **/
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPathname() { this.pathname = pathname; }


    public void setImage(String image) { this.image = image; }
}
