package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.allteran.sellpo.domain.RepairRequest;
import ru.allteran.sellpo.validator.RepairRequestValidator;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RepairController {

    @Autowired
    private RepairRequestValidator requestValidator;

    @GetMapping("/createRepairRequest")
    public String repairRequestForm() {
        return "createRepairRequest";
    }

    @PostMapping("/createRepairRequest")
    public String createRepairRequest(
            @Valid RepairRequest repairRequest,
            BindingResult bindingResult, Model model) {
        requestValidator.validate(repairRequest, bindingResult);
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("requestDraft", repairRequest);
            return "createRepairRequest";
        }

        return "createRepairRequest";
    }
}
