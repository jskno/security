package com.baeldung.componentscanautoconfigure.employee;

import org.springframework.stereotype.Component;

@Component("employee")
public class Employee {

    @Override
    public String toString() {
        return "Employee" + this.hashCode();
    }
}
