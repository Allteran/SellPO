package ru.allteran.sellpo.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ControllerUtils {
//    static Map<String, String> getErrors(BindingResult bindingResult) {
//        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap(
//                fieldError -> fieldError.getField() + "Error",
//                FieldError::getDefaultMessage
//        );
//        return bindingResult.getFieldErrors().stream().collect(collector);
//    }

    static Map<String, String> getFieldErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            errors.put(bindingResult.getFieldErrors().get(i).getField() + "Error",
                    bindingResult.getFieldErrors().get(i).getCode());
        }

        return errors;
    }
}
