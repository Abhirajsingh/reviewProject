package com.zemoso.project.model;

import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;

@Data
@Entity
public class Skill {

    public Skill(){
        this.name = "not available";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String name;

    private Long companyId;

}
