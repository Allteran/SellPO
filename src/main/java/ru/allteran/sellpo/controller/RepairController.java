package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.allteran.sellpo.domain.RepairRequest;
import ru.allteran.sellpo.service.ExcelService;
import ru.allteran.sellpo.service.RepairService;
import ru.allteran.sellpo.validator.RepairRequestValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RepairController {
    private static final String SUCCESS_REQUEST_CREATED = "Заявка успешно создана";
    @Autowired
    private RepairService repairService;
    @Autowired
    private ExcelService excelService;

    @Autowired
    private RepairRequestValidator requestValidator;

    @GetMapping("/createRepairRequest")
    public String repairRequestForm() {
        return "createRepairRequest";
    }

    @GetMapping("/repairlist")
    public String repairRequestList(Model model) {
        List<RepairRequest> requestList = repairService.findAll();
        model.addAttribute("requests", requestList);
        return "repairRequestList";
    }

    @PostMapping(value = "/createRepairRequest", params = {"createRequest"})
    public String createRepairRequest(
            @Valid RepairRequest repairRequest,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        requestValidator.validate(repairRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("requestDraft", repairRequest);
            return "createRepairRequest";
        }
        repairService.createRepairRequest(repairRequest);
        redirectAttributes.addFlashAttribute("successMessage", SUCCESS_REQUEST_CREATED);

        return "redirect:/repairlist";
    }

    @PostMapping(value = "/createRepairRequest", params = {"generateCertificate"})
    public String generateCertificate(
            @Valid RepairRequest repairRequest, BindingResult bindingResult,
            Model model) {
        requestValidator.validate(repairRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("requestDraft", repairRequest);
            return "createRepairRequest";
        }
        //TODO: generate document transfer acceptance certificate in excel or PDF and download it
        excelService.generateAcceptanceCertificate(repairRequest);
        return "createRepairRequest";
    }
}
