package com.learning.Spring_Dept.repository;

import com.learning.Spring_Dept.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    //<Entity, DataType of Entity>

    public Department findByDepartmentName(String departmentName);

}
