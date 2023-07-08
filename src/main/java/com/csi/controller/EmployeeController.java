package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp( @Valid @RequestBody  Employee employee) {
        return new  ResponseEntity<>(employeeServiceImpl.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getdatabyname/{empName}")
    public  ResponseEntity<List<Employee>>getDataByEmpName(@PathVariable String empName){
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmpName(empName));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee>getDataByEmpContactNumber(@PathVariable long empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmpContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
    public ResponseEntity<Employee>getDataByEmpEmailId(@PathVariable String empEmailId){
        return ResponseEntity.ok(employeeServiceImpl.getDataByEmpEmailId(empEmailId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>>getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @PutMapping("/updatedata/{empId}")

    public ResponseEntity<Employee>updateData(@Valid @RequestBody Employee employee,int empId) {

        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Employee Id Does Not Exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDob(employee.getEmpDob());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        return new ResponseEntity<>(employeeServiceImpl.updateData(employee1), HttpStatus.CREATED);
    }

        @PostMapping("/savebulkofdata")
         public ResponseEntity<List<Employee>>saveAll(@Valid @RequestBody List<Employee>employees){
        return new ResponseEntity<>(employeeServiceImpl.saveBulkOfData(employees),HttpStatus.CREATED);

    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>>sortById(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>>sortByName(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>>sortBySalary(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }
    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>>sortByDob(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDob)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>>filterBySalary(@PathVariable double empSalary){
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp->emp.getEmpSalary()>=empSalary).collect(Collectors.toList()));
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String>deleteDataById(@PathVariable int empId){
        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }







}
