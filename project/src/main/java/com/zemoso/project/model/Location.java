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

    public Location(Long id , Long companyId , String country , String state , String city, String landmark){
        this.landmark=landmark;
        this.city=city;
        this.country=country;
        this.companyId=companyId;
        this.state = state;
        this.id=id;
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
