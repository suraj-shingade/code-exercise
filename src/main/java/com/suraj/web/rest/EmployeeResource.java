package com.suraj.web.rest;

import com.suraj.aggregator.EmployeeAggregator;
import com.suraj.domain.Employee;
import com.suraj.web.rest.errors.BadRequestAlertException;
import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST controller for managing {@link com.suraj.domain.Employee}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private static final String ENTITY_NAME = "codeExerciseSurajEmployee";
    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);
    private final EmployeeAggregator employeeAggregator;
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public EmployeeResource(EmployeeAggregator employeeAggregator) {
        this.employeeAggregator = employeeAggregator;
    }

    @PutMapping("/employees/place/{place}/salary/{percentage}")
    public ResponseEntity<Integer> updateEmployeeSalary(@PathVariable String place, @PathVariable Float percentage) {
        log.debug("REST request to update Employee salary by % {} at place {} ", percentage, percentage);
        if (null == place || null == percentage) {
            throw new BadRequestAlertException("Place & Percentage Required", place, "percentage");
        }
        Integer affectedRows = employeeAggregator.updateSalaryByPlace(place, percentage);
        return ResponseEntity.ok().body(affectedRows);
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        log.debug("REST request to update Employee {} ", employee.getId());
        if (null == employee.getId()) {
            throw new BadRequestAlertException("Employee dosnt exists", "", "");
        }
        employee = employeeAggregator.save(employee);
        return ResponseEntity.ok().body(employee);
    }

    @GetMapping("/employees/all")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        log.debug("REST request to fetchAllEmployees");
        return ResponseEntity.ok().body(employeeAggregator.findAll());
    }

    @GetMapping("/employees/by/supervisor/{id}")
    public ResponseEntity<List<Employee>> fetchAllSupervisor(@PathVariable Long id) {
        log.debug("REST request to fetchAllSupervisor : {} ", id);
        if (null == id) {
            throw new BadRequestAlertException("Employee is not Supervisor of anyone", "", "");
        }
        List<Employee> employeeList = employeeAggregator.findBySupervisorID(id);
        return ResponseEntity.ok().body(employeeList);
    }

    @GetMapping("/employees/by/place/{place}")
    public ResponseEntity<List<Employee>> findByPlace(@PathVariable String place) {
        log.debug("REST request to findByPlace : {}", place);
        return ResponseEntity.ok().body(employeeAggregator.findByPlace(place));
    }

    @GetMapping("/employees/salary/total/by/bu/{bu}")
    public ResponseEntity<Double> totalSalaryByBusinessUnit(@PathVariable String bu) {
        log.debug("REST request to totalSalaryByBusinessUnit : {}", bu);
        return ResponseEntity.ok().body(employeeAggregator.salarySumByBusinessUnit(bu));
    }

    @GetMapping("/employees/salary/total/by/place/{place}")
    public ResponseEntity<Double> totalSalaryByPlace(@PathVariable String place) {
        log.debug("REST request to totalSalaryByPlace : {}", place);
        return ResponseEntity.ok().body(employeeAggregator.salarySumByPlace(place));
    }

    @GetMapping("/employees/salary/total/by/supervisor/{supervisor}")
    public ResponseEntity<Double> totalSalaryBySupervisor(@PathVariable Long supervisor) {
        log.debug("REST request to totalSalaryBySupervisor : {}", supervisor);
        return ResponseEntity.ok().body(employeeAggregator.salarySumBySupervisor(supervisor));
    }

    @GetMapping("/employees/salary/range/by/title/{title}")
    public ResponseEntity<Range> totalSalaryBySupervisor(@PathVariable String title) {
        log.debug("REST request to totalSalaryBySupervisor : {}", title);
        return ResponseEntity.ok().body(employeeAggregator.salaryRange(title));
    }
}
