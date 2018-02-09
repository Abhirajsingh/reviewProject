package com.zemoso.project.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Employee extends BaseEntityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Project project;

    @Column(name = "project_id", insertable = false, updatable = false)
    private Long projectId;

    private String employeeRole;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Location location;

    @Column(name = "location_id", insertable = false, updatable = false)
    private Long locationId;

    private String startDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;

    @Column(name = "department_id", insertable = false, updatable = false)
    private Long departmentId;

    private String reportingEmployeeName;

    private Long reportingEmployeeId;

    private String profilePic;

    public String getFullName() {
        String fullName = "";
        if (this.firstName != null)
            fullName += this.firstName + " ";
        if (this.middleName != null)
            fullName += this.middleName + " ";
        if (this.lastName != null)
            fullName += this.lastName + " ";

        return fullName;
    }
}
