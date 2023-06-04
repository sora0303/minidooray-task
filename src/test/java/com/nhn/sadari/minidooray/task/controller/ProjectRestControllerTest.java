package com.nhn.sadari.minidooray.task.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterResponse;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void createProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        ProjectRegisterRequest projectRegisterRequest = new ProjectRegisterRequest("test", "test", 1L);
        HttpEntity<ProjectRegisterRequest> request = new HttpEntity<>(projectRegisterRequest, headers);

        ResponseEntity<ProjectRegisterResponse> result = testRestTemplate.postForEntity(
            "/api/projects",
            request,
            ProjectRegisterResponse.class);

        ProjectRegisterResponse projectRegisterResponse = new ProjectRegisterResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(projectRegisterResponse);

    }
}