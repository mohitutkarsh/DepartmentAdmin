package com.learning.Spring_Dept.service;

import com.learning.Spring_Dept.entity.Department;
import com.learning.Spring_Dept.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().
                departmentName("IT").
                departmentId(1L).
                departmentAddress("Bangalore").
                departmentCode("IT-04").build();

        Mockito.when(departmentRepository.findByDepartmentName("IT")).
                thenReturn(department);
    }

    @Test
    @DisplayName("Get data based on department name")
    public void whenValidDepartmentName_thenSuccess() {
        String departmentName = "IT";

        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}