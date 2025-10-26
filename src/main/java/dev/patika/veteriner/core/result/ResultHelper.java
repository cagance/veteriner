package dev.patika.veteriner.core.result;

import org.springframework.http.HttpStatus;

public class ResultHelper {

    public static <T> ResultData<T> created(T data) {
        return new ResultData<>(true, "Created", "201", data);
    }

    public static <T> ResultData<T> success(T data) {
        return new ResultData<>(true, "Success", "200", data);
    }

    public static Result ok() {
        return new Result(true, "OK", "200");
    }

    public static Result notFoundError(String message) {
        return new Result(false, message, "404");
    }

    public static Result duplicateError(String message) {
        return new Result(false, message, "400");
    }

    public static Result validationError(String message) {
        return new Result(false, message, "400");
    }
}
