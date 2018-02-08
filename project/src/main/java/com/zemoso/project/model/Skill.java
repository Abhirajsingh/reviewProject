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

    public Skill(Long id , Long companyId , String name){
        this.id=id;
        this.companyId=companyId;
        this.name=name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NonNull
    private String name;

    private Long companyId;

}
