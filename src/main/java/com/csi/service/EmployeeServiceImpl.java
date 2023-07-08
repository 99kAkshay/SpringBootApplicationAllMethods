package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeDaoImpl employeeDaoImpl;



    public Employee signUp(Employee employee){
        return employeeDaoImpl.signUp(employee);
    }

    public boolean signIn(String empEmailId,String emPassword){
       return employeeDaoImpl.signIn(empEmailId,emPassword);
    }

    @Cacheable(value = "empId")
    public Optional<Employee> getDataById(int empId){
        return employeeDaoImpl.getDataById(empId);
    }

    public List<Employee>getAllData(){
        return employeeDaoImpl.getAllData();
    }

    public List<Employee>getDataByEmpName(String empName){
        return employeeDaoImpl.getDataByEmpName(empName);
    }

    public Employee getDataByEmpContactNumber(long empContactNumber){
        return employeeDaoImpl.getDataByEmpContactNumber(empContactNumber);
    }

    public Employee getDataByEmpEmailId(String empEmailId){
        return employeeDaoImpl.getDataByEmpEmailId(empEmailId);
    }
    public List<Employee> saveBulkOfData(List<Employee> employees) {
        return employeeDaoImpl.saveBulkOfData(employees);
    }


    public Employee updateData(Employee employee){
        return employeeDaoImpl.updateData(employee);
    }
    public void deleteById(int empId){
        employeeDaoImpl.deleteById(empId);
    }




}
