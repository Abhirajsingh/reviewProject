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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private long companyId;

    @NonNull
    private String name;

}
