package com.zemoso.project.model;


import lombok.Data;
import lombok.NonNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Role {

    public Role(){
        this.name = "not avaialble";
    }

    public Role(Long id, Long companyId , String name){
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
