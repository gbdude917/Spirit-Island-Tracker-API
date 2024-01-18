package com.sitracker.sigametracker.entity;


import jakarta.persistence.*;


@Entity
@Table(name = "adversaries")
public class Adversary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String flag;

    /** Getters **/
    public Long getId() { return id; }

    public String getName() { return name; }

    public String getFlag() { return flag; }

    /** Setters **/
    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setFlag(String flag) { this.flag = flag; }}
