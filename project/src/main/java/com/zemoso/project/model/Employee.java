package com.zemoso.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Employee extends BaseEntityModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long companyId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String biodata;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Skill> skill;

    private String email;
    private String mobileNo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Project project;

    private String employeeRole;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;
    private String startDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    private String reportingEmployeeName;

    private Long reportingEmployeeId;

    private String profilePic;

    public String getFullName(){
        String fullName = "";
        if(this.firstName != null)
            fullName+=this.firstName + " ";
        if(this.middleName !=null)
            fullName+=this.middleName + " ";
        if(this.lastName != null)
            fullName+=this.lastName + " ";

        return fullName;
    }
}
