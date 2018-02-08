package com.zemoso.project.model;

import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;


@Data
@Entity
public class Project {

    public Project(){
        this.name="not available";
    }

    public Project(Long id, Long companyId , String name){
        this.id=id;
        this.companyId=companyId;
        this.name=name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private long companyId;

    @NonNull
    private String name;

}
