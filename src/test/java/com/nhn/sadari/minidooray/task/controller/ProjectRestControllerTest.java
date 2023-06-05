package com.nhn.sadari.minidooray.task.controller;

import com.nhn.sadari.minidooray.task.domain.ProjectDto;
import com.nhn.sadari.minidooray.task.domain.ProjectModifyRequest;
import com.nhn.sadari.minidooray.task.domain.ProjectRegisterRequest;
import com.nhn.sadari.minidooray.task.domain.IdResponse;
import com.nhn.sadari.minidooray.task.enumclass.ProjectStatusType;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectRestControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("프로젝트 등록")
    @Order(1)
    void testCreateProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        ProjectRegisterRequest projectRegisterRequest = new ProjectRegisterRequest("test", "test", 1L, "홍길동");
        HttpEntity<ProjectRegisterRequest> request = new HttpEntity<>(projectRegisterRequest, headers);

        ResponseEntity<IdResponse> result = testRestTemplate.postForEntity(
            "/api/projects",
            request,
            IdResponse.class);

        IdResponse response = new IdResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(response);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("프로젝트 수정")
    @Order(2)
    void testModifyProject() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        ProjectModifyRequest projectModifyRequest = new ProjectModifyRequest("test_modify", "test_modify", ProjectStatusType.휴면);
        HttpEntity<ProjectModifyRequest> request = new HttpEntity<>(projectModifyRequest, headers);

        ResponseEntity<IdResponse> result = testRestTemplate.exchange(
            "/api/projects/{projectId}",
            HttpMethod.PUT,
            request,
            IdResponse.class,
            1L);

        IdResponse response = new IdResponse(1L);

        Assertions.assertThat(result.getBody()).isEqualTo(response);
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

//    @Test
//    @DisplayName("프로젝트 삭제")
//    @Order(3)
//    void testDeleteProject() throws Exception{
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<IdResponse> result = testRestTemplate.exchange(
//            "/api/projects/{projectId}",
//            HttpMethod.DELETE,
//            requestEntity,
//            IdResponse.class,
//            1L
//        );
//
//        IdResponse response = new IdResponse(1L);
//
//        Assertions.assertThat(result.getBody()).isEqualTo(response);
//        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }

    @Test
    @DisplayName("프로젝트 아이디로 프로젝트 조회")
    @Order(4)
    void testGetProjectByProjectId() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity request = new HttpEntity(headers);

        ResponseEntity<ProjectDto> result = testRestTemplate.exchange(
            "/api/projects/{projectId}",
            HttpMethod.GET,
            null,
            ProjectDto.class,
            1L);

        Assertions.assertThat(result.getBody().getName()).isEqualTo("test_modify");
        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}