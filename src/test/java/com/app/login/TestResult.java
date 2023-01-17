package com.app.login;

import com.app.login.result.ClientError;
import com.app.login.result.Result;
import com.app.login.result.ServerError;
import com.app.login.result.Success;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class TestResult {

    @Test
    void test_result_class_with_success() {
        ResponseEntity result = getResultResponseEntity(new Success("krunal"));
        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        Assertions.assertEquals("krunal", result.getBody());
    }

    @Test
    void test_result_class_with_success_and_output_test() {
        ResponseEntity result = getResultResponseEntity(new Success(new com.app.login.Test("krunal")));
        Assertions.assertEquals(result.getStatusCodeValue(), 200);
        com.app.login.Test t = (com.app.login.Test) result.getBody();
        Assertions.assertEquals("krunal", t.getData());
    }

    @Test
    void test_result_class_with_error_400() {
        ResponseEntity result = getResultResponseEntity(new ClientError(new Exception("Something went wrong")));
        Assertions.assertEquals(result.getStatusCodeValue(), 400);
        Assertions.assertEquals("Something went wrong", result.getBody());
    }

    @Test
    void test_result_class_with_error_500() {
        ResponseEntity result = getResultResponseEntity(new ServerError(new Exception("Something went wrong")));
        Assertions.assertEquals(result.getStatusCodeValue(), 500);
        Assertions.assertEquals("Something went wrong", result.getBody());
    }

    ResponseEntity getResultResponseEntity(Result result) {
        ResponseEntity responseEntity = null;
        if (result instanceof Success) {
            Success success = (Success) result;
            responseEntity = ResponseEntity.ok(success.getData());
        } else if (result instanceof ClientError) {
            ClientError clientError = (ClientError) result;
            responseEntity = ResponseEntity.badRequest().body(clientError.getException().getMessage());
        } else if (result instanceof ServerError) {
            ServerError serverError = (ServerError) result;
            responseEntity = ResponseEntity.internalServerError().body(serverError.getException().getMessage());
        }
        return responseEntity;
    }

}


