package com.suraj.aggregator;

import com.suraj.domain.Employee;
import com.suraj.dto.EmployeeDTO;
import com.suraj.service.EmployeeService;
import com.suraj.utils.CustomCache;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeAggregator {

    @Autowired
    EmployeeService employeeService;

    public void saveAll(List<EmployeeDTO> employeeDTOS) {
        employeeDTOS.stream().forEach(employeeDTO -> {
            employeeService.save(employeeDTO.toEntity());
        });
    }

    public void save(List<Employee> employees) {
        employees.stream().forEach(employee -> {
            employeeService.save(employee);
        });
    }

    public int updateSalaryByPlace(String place, Float percentage) {
        List<Employee> employeeList = this.findByPlaceContainingIgnoreCase(place);
        employeeList = employeeList.stream()
            .map(employee -> {
                employee.increaseSalaryByPer(percentage);
                return employee;
            })
            .collect(Collectors.toList());
        return employeeService.saveAll(employeeList);
    }

    public List<Employee> findAll() {
        return employeeService.findAll(Pageable.unpaged()).getContent();
    }

    public Employee save(Employee employee) {
        CustomCache.save(employee);
        return employeeService.save(employee);
    }

    public  List<Employee> findByPlaceContainingIgnoreCase(String place) {
        return employeeService.findByPlaceContainingIgnoreCase(place);
    }

    public List<Employee> findByPlace(String place) {
        Collection<Object> employeeList = CustomCache.loadAll();
        if (employeeList.isEmpty()) {
            return this.findByPlaceContainingIgnoreCase(place);
        }
        return employeeList.stream().map(o -> (Employee) o).collect(Collectors.toList());
    }

    public List<Employee> findBySupervisorID(Long supervisorID) {
        return employeeService.findBySupervisorID(new Employee(supervisorID));
    }

    public List<Employee> findByBusinessUnitContainingIgnoreCase(String businessUnit) {
        return employeeService.findByBusinessUnitContainingIgnoreCase(businessUnit);
    }

    public Double salarySumByBusinessUnit(String bu) {
        List<Employee> employeeList = findByBusinessUnitContainingIgnoreCase(bu);
        return getSalarySum(employeeList);
    }

    public Double salarySumBySupervisor(Long supervisor) {
        List<Employee> employeeList = findBySupervisorID(supervisor);
        return getSalarySum(employeeList);
    }

    public Double salarySumByPlace(String place) {
        List<Employee> employeeList = this.findByPlaceContainingIgnoreCase(place);
        return getSalarySum(employeeList);
    }

    public Range salaryRange(String title) {
        List<Employee>  employeeList = employeeService.findByTitleContainingIgnoreCase(title);
        if(employeeList.isEmpty()) return null;
        Collections.sort(employeeList, Comparator.comparing(Employee::getSalary).reversed());
        return Range.between(employeeList.get(0).getSalary(), employeeList.get(employeeList.size()-1).getSalary());
    }

    private Double getSalarySum(List<Employee> employeeList ) {
        return employeeList.stream().mapToDouble(Employee::getSalary).sum();
    }
}
