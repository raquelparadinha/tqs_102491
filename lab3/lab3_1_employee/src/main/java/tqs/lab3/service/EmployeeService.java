package tqs.lab3.service;

import java.util.List;

import tqs.lab3.data.Employee;

public interface EmployeeService {
    public Employee getEmployeeById(Long id);

    public Employee getEmployeeByName(String name);

    public List<Employee> getAllEmployees();

    public boolean exists(String email);

    public Employee save(Employee employee);

}
