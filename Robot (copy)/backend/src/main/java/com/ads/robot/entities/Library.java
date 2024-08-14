package com.ads.robot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "library",fetch = FetchType.LAZY)
    private List<FunctionRobot> functions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FunctionRobot> getFunctions() {
        return functions;
    }

    public void setFunctions(List<FunctionRobot> functions) {
        this.functions = functions;
    }
}
