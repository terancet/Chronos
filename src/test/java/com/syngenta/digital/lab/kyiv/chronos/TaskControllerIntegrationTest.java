package com.syngenta.digital.lab.kyiv.chronos;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.syngenta.digital.lab.kyiv.chronos.model.dto.TaskDto;
import com.syngenta.digital.lab.kyiv.chronos.model.response.GeneralResponse;
import com.syngenta.digital.lab.kyiv.chronos.utils.JsonUtils;
import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.springtestdbunit.assertion.DatabaseAssertionMode.NON_STRICT_UNORDERED;

@DatabaseTearDown("/TaskControllerIntegrationTest/dbTearDown.xml")
public class TaskControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @SneakyThrows
    @DatabaseSetup(value = "/TaskControllerIntegrationTest/shouldAddNewTask/dbSetup.xml")
    @ExpectedDatabase(value = "/TaskControllerIntegrationTest/shouldAddNewTask/expectedDataBase.xml",
            assertionMode = NON_STRICT_UNORDERED)
    public void shouldAddNewTask() {

        Response response = RestAssured
                .given()
                .body(JsonUtils.readFromJson("/TaskControllerIntegrationTest/shouldAddNewTask/addNewTaskRequest.json", TaskDto.class))
                .contentType(ContentType.JSON)
                .post("/api/v0/task")
                .then()
                .extract()
                .response();

        String asGeneralResponseString = response.getBody().asString();
        GeneralResponse<TaskDto> actualResponse = this.objectMapper.readValue(asGeneralResponseString, new TypeReference<GeneralResponse<TaskDto>>() {
        });

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        Assertions.assertThat(actualResponse).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotNull();
        Assertions.assertThat(actualResponse.getData().getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().getComments()).isEqualTo("comments");
        Assertions.assertThat(actualResponse.getData().getReportingDate()).isEqualTo(LocalDate.of(2019,1,1));
        Assertions.assertThat(actualResponse.getData().getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().getTags()).isEqualTo("tag");

    }

    @Test
    @SneakyThrows
    @DatabaseSetup(value = "/TaskControllerIntegrationTest/shouldGetTaskByItsId/dbSetup.xml")
    @ExpectedDatabase(value = "/TaskControllerIntegrationTest/shouldGetTaskByItsId/expectedDataBase.xml",
            assertionMode = NON_STRICT_UNORDERED)
    public void shouldGetTaskByItsId() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v0/task/1")
                .then()
                .extract()
                .response();

        String asGeneralResponseString = response.getBody().asString();
        GeneralResponse<TaskDto> actualResponse = this.objectMapper.readValue(asGeneralResponseString, new TypeReference<GeneralResponse<TaskDto>>() {
        });

        Assertions.assertThat(response.getStatusCode()).isEqualTo(302);
        Assertions.assertThat(actualResponse).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotNull();
        Assertions.assertThat(actualResponse.getData().getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().getComments()).isEqualTo("comments");
        Assertions.assertThat(actualResponse.getData().getReportingDate()).isEqualTo(LocalDate.of(2019,1,1));
        Assertions.assertThat(actualResponse.getData().getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().getTags()).isEqualTo("tag");

    }

    @Test
    @SneakyThrows
    @DatabaseSetup(value = "/TaskControllerIntegrationTest/shouldGetTasksByUserId/dbSetup.xml")
    @ExpectedDatabase(value = "/TaskControllerIntegrationTest/shouldGetTasksByUserId/expectedDataBase.xml",
            assertionMode = NON_STRICT_UNORDERED)
    public void shouldGetTasksByUserId() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("/api/v0/user/1/task")
                .then()
                .extract()
                .response();

        String asGeneralResponseString = response.getBody().asString();
        GeneralResponse<List<TaskDto>> actualResponse = this.objectMapper.readValue(asGeneralResponseString, new TypeReference<GeneralResponse<List<TaskDto>>>() {
        });

        Assertions.assertThat(response.getStatusCode()).isEqualTo(302);
        Assertions.assertThat(actualResponse).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotEmpty();

        Assertions.assertThat(actualResponse.getData().get(0).getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().get(0).getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(0).getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(0).getComments()).isEqualTo("comments");
        Assertions.assertThat(actualResponse.getData().get(0).getReportingDate()).isEqualTo(LocalDate.of(2019,1,1));
        Assertions.assertThat(actualResponse.getData().get(0).getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().get(0).getTags()).isEqualTo("tag");

        Assertions.assertThat(actualResponse.getData().get(1).getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().get(1).getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(1).getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(1).getComments()).isEqualTo("comments");
        Assertions.assertThat(actualResponse.getData().get(1).getReportingDate()).isEqualTo(LocalDate.of(2019,1,1));
        Assertions.assertThat(actualResponse.getData().get(1).getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().get(1).getTags()).isEqualTo("tag");
    }

    @Test
    @SneakyThrows
    @DatabaseSetup(value = "/TaskControllerIntegrationTest/shouldFindAllTasksForUserIdAndTimePeriod/dbSetup.xml")
    @ExpectedDatabase(value = "/TaskControllerIntegrationTest/shouldFindAllTasksForUserIdAndTimePeriod/expectedDataBase.xml",
            assertionMode = NON_STRICT_UNORDERED)
    public void shouldFindAllTasksForUserIdAndTimePeriod() {
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .param("start", "01/01/2019")
                .param("end", "01/02/2019")
                .get("/api/v0/user/1")
                .then()
                .extract()
                .response();

        String asGeneralResponseString = response.getBody().asString();
        GeneralResponse<List<TaskDto>> actualResponse = this.objectMapper.readValue(asGeneralResponseString, new TypeReference<GeneralResponse<List<TaskDto>>>() {
        });

        Assertions.assertThat(response.getStatusCode()).isEqualTo(302);
        Assertions.assertThat(actualResponse).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotEmpty();

        Assertions.assertThat(actualResponse.getData().get(0).getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().get(0).getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(0).getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(0).getComments()).isEqualTo("comments");
        Assertions.assertThat(actualResponse.getData().get(0).getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().get(0).getTags()).isEqualTo("tag");

        Assertions.assertThat(actualResponse.getData().get(1).getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().get(1).getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(1).getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().get(1).getComments()).isEqualTo("comments");
        Assertions.assertThat(actualResponse.getData().get(1).getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().get(1).getTags()).isEqualTo("tag");

        List<LocalDate> reportingDates = actualResponse.getData()
                .stream()
                .map(TaskDto::getReportingDate)
                .collect(Collectors.toList());

        Assertions.assertThat(reportingDates).isNotEmpty();
        Assertions.assertThat(reportingDates)
                .isEqualTo(List.of(LocalDate.of(2019,1,1),LocalDate.of(2019,2,1)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup(value = "/TaskControllerIntegrationTest/shouldUpdateTheExistingTask/dbSetup.xml")
    @ExpectedDatabase(value = "/TaskControllerIntegrationTest/shouldUpdateTheExistingTask/expectedDataBase.xml",
            assertionMode = NON_STRICT_UNORDERED)
    public void shouldUpdateTheExistingTask() {

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(JsonUtils.readFromJson("/TaskControllerIntegrationTest/shouldUpdateTheExistingTask/updateTheExistingTaskRequest.json", TaskDto.class))
                .put("/api/v0/task")
                .then()
                .extract()
                .response();

        String asGeneralResponseString = response.getBody().asString();
        GeneralResponse<TaskDto> actualResponse = this.objectMapper.readValue(asGeneralResponseString, new TypeReference<GeneralResponse<TaskDto>>() {
        });

        Assertions.assertThat(response.getStatusCode()).isEqualTo(302);
        Assertions.assertThat(actualResponse).isNotNull();
        Assertions.assertThat(actualResponse.getData()).isNotNull();
        Assertions.assertThat(actualResponse.getData().getTaskId()).isNotNull();
        Assertions.assertThat(actualResponse.getData().getProjectId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().getUserId()).isEqualTo(1);
        Assertions.assertThat(actualResponse.getData().getComments()).isEqualTo("new comments");
        Assertions.assertThat(actualResponse.getData().getReportingDate()).isEqualTo(LocalDate.of(2019,1,1));
        Assertions.assertThat(actualResponse.getData().getSpentTime()).isEqualTo(0.8f);
        Assertions.assertThat(actualResponse.getData().getTags()).isEqualTo("tag");
    }

    @Test
    @SneakyThrows
    @DatabaseSetup(value = "/TaskControllerIntegrationTest/shouldUpdateTheExistingTask/dbSetup.xml")
    @ExpectedDatabase(value = "/TaskControllerIntegrationTest/shouldUpdateTheExistingTask/expectedDataBase.xml",
            assertionMode = NON_STRICT_UNORDERED)
    public void shouldDeleteTheExistingTask() {

        Response response = RestAssured
                .given()
                .delete("/api/v0/task/1")
                .then()
                .extract()
                .response();

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
