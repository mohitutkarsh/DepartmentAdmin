package com.learning.Spring_Dept.controller;

import com.learning.Spring_Dept.entity.Department;
import com.learning.Spring_Dept.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder().
                departmentAddress("Bangalore").
                departmentCode("IT-04").
                departmentId(1L).
                departmentName("IT").build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder().
                departmentAddress("Bangalore").
                departmentCode("IT-04").
                departmentName("IT").build();

        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments").
                contentType(MediaType.APPLICATION_JSON).
                content("{\n" +
                        "  \"departmentName\" : \"IT\",\n" +
                        "  \"departmentAddress\": \"Bangalore\",\n" +
                        "  \"departmentCode\": \"IT-04\"\n" +
                        "}")).
                andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.departmentName").
                        value(department.getDepartmentName()));
    }
}