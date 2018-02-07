package com.zemoso.project.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
public class Location {

    public Location(){
        this.landmark="not available";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long companyId;
    private String country;
    private String state;
    private String city;

    @NotNull
    private String landmark;

}
