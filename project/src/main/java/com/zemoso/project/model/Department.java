package com.zemoso.project.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Department {

    public Department() {
        this.name = "not available";
    }

    public Department(String name, Long companyId, Long id) {
        this.name = name;
        this.companyId = companyId;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    private Long companyId;

}
