package com.suraj.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employee")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // Source is csv so commented for now
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "title")
    private String title;

    @Column(name = "business_unit")
    private String businessUnit;

    @Column(name = "place")
    private String place;

    @Column(name = "competencies")
    private String competencies;

    @Column(name = "salary")
    private Float salary;

    @OneToOne
    @JoinColumn
    private Employee supervisorID;

    public Employee(Long supervisorID) {
        this.id = supervisorID;
    }


    public Employee() {
    }

    public static Employee create() {
        return new Employee();
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
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

    public Employee employeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Employee title(String title) {
        this.title = title;
        return this;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Employee businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Employee place(String place) {
        this.place = place;
        return this;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }

    public Employee competencies(String competencies) {
        this.competencies = competencies;
        return this;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Employee salary(Float salary) {
        this.salary = salary;
        return this;
    }

    public Employee getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(Employee employee) {
        this.supervisorID = employee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    public Employee supervisorID(Employee employee) {
        this.supervisorID = employee;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return id != null && id.equals(((Employee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", employeeName='" + getEmployeeName() + "'" +
            ", title='" + getTitle() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", place='" + getPlace() + "'" +
            ", competencies='" + getCompetencies() + "'" +
            ", salary=" + getSalary() +
            "}";
    }

    public void increaseSalaryByPer(Float percentage) {
        this.salary = this.salary + (this.salary * percentage / 100);
    }

    public Employee self() {
        return this;
    }
}
