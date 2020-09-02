package com.suraj.dto;


import com.google.common.primitives.Floats;
import com.google.common.primitives.Longs;
import com.suraj.domain.Employee;
import io.jsonwebtoken.lang.Strings;

import java.io.Serializable;

/**
 * A Employee.
 */
public class EmployeeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String employeeName;

    private String title;

    private String businessUnit;

    private String place;

    private String competencies;

    private Float salary;

    private Long supervisorID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Long getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(Long supervisorID) {
        this.supervisorID = supervisorID;
    }

    public void extractEmployeeId(String s) {
        this.id = Longs.tryParse(s);
    }

    public void extractEmployeeName(String s) {
        this.employeeName = Strings.clean(s);
    }

    public void extractTitle(String s) {
        this.title = Strings.clean(s);
    }

    public void extractBusinessUnit(String s) {
        this.businessUnit = Strings.clean(s);
    }

    public void extractPlace(String s) {
        this.place = Strings.clean(s);
    }

    public void extractSupervisorID(String s) {
        this.supervisorID = Longs.tryParse(s);
    }

    public void extractCompetencies(String s) {
        this.competencies = Strings.clean(s);
    }

    public void extractSalary(String s) {
        this.salary = Floats.tryParse(s);
    }

    public Employee toEntity() {
        Employee employee = Employee.create();
        employee.setId(this.id);
        employee.setBusinessUnit(this.businessUnit);
        employee.setEmployeeName(this.employeeName);
        employee.setTitle(this.title);
        employee.setPlace(this.place);
        employee.setCompetencies(this.competencies);
        employee.setSalary(this.salary);
        employee.setSupervisorID(null == this.supervisorID ? null : new Employee(this.supervisorID));
        return employee;
    }
}
