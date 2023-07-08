package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepo employeeRepoImpl;

    public Employee signUp(Employee employee){
        return employeeRepoImpl.save(employee);
    }

    public boolean signIn(String empEmailId,String emPassword){
        boolean flag=false;

        if(employeeRepoImpl.findByEmpEmailIdAndEmpPassword(empEmailId,emPassword).size()>1){
            flag=true;
        }
        return flag;
    }

    public Optional<Employee> getDataById(int empId){
        return employeeRepoImpl.findById(empId);
    }

    public List<Employee>getAllData(){
        return employeeRepoImpl.findAll();
    }

    public List<Employee>getDataByEmpName(String empName){
        return employeeRepoImpl.findByEmpName(empName);
    }

    public Employee getDataByEmpContactNumber(long empContactNumber){
        return employeeRepoImpl.findByEmpContactNumber(empContactNumber);
    }
    public List<Employee> saveBulkOfData(List<Employee> employees) {
        return employeeRepoImpl.saveAll(employees);
    }


    public Employee getDataByEmpEmailId(String empEmailId){
        return employeeRepoImpl.findByEmpEmailId(empEmailId);
    }

    public Employee updateData(Employee employee){
        return employeeRepoImpl.save(employee);
    }
    public void deleteById(int empId){
        employeeRepoImpl.deleteById(empId);
    }

}
