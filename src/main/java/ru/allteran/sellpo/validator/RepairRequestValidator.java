package ru.allteran.sellpo.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.allteran.sellpo.domain.RepairRequest;


@Component
public class RepairRequestValidator implements Validator {
    @Value("${validator.empty}")
    private String emptyMessage;
    @Value("${validator.phone}")
    private String phoneMessage;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        RepairRequest request = (RepairRequest) o;
        ValidationUtils.rejectIfEmpty(errors, "productType", emptyMessage);
        ValidationUtils.rejectIfEmpty(errors, "productName", emptyMessage);
        ValidationUtils.rejectIfEmpty(errors, "requestReason", emptyMessage);
        ValidationUtils.rejectIfEmpty(errors, "equipSet", emptyMessage);
        ValidationUtils.rejectIfEmpty(errors, "clientName", emptyMessage);
        ValidationUtils.rejectIfEmpty(errors, "clientPhone", emptyMessage);

        if (!StringUtils.hasText(request.getProductType())) {
            errors.rejectValue("productType", emptyMessage);
        }
        if (!StringUtils.hasText(request.getProductName())) {
            errors.rejectValue("productName", emptyMessage);
        }
        if (!StringUtils.hasText(request.getRequestReason())) {
            errors.rejectValue("requestReason", emptyMessage);
        }
        if (!StringUtils.hasText(request.getEquipSet())) {
            errors.rejectValue("equipSet", emptyMessage);
        }
        if (!StringUtils.hasText(request.getClientName())) {
            errors.rejectValue("clientName", emptyMessage);
        }

        if (!StringUtils.isEmpty(request.getClientPhone())) {
            if (request.getClientPhone().length() != 11) {
                errors.rejectValue("clientPhone", phoneMessage);
            }

            if (!request.getClientPhone().matches("\\^?(79)\\d{9}")) {
                errors.rejectValue("clientPhone", phoneMessage);
            }
        }
    }
}
