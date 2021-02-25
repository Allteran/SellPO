package ru.allteran.sellpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.allteran.sellpo.domain.PointOfSales;
import ru.allteran.sellpo.domain.RepairRequest;
import ru.allteran.sellpo.domain.User;
import ru.allteran.sellpo.repo.POSRepository;
import ru.allteran.sellpo.service.ExcelService;
import ru.allteran.sellpo.service.RepairService;
import ru.allteran.sellpo.validator.RepairRequestValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLConnection;
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
    @Autowired
    private POSRepository posRepo;

    private String certificatePath;

    @GetMapping("/createRepairRequest")
    public String repairRequestForm(Model model) {
        model.addAttribute("posList", posRepo.findAll());
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
            @Valid RepairRequest repairRequest, @RequestParam Map<String, String> form,
            BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        requestValidator.validate(repairRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("requestDraft", repairRequest);
            return "createRepairRequest";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        repairService.createRepairRequest(repairRequest, user, form);
        redirectAttributes.addFlashAttribute("successMessage", SUCCESS_REQUEST_CREATED);
        //TODO: check if it fill right data into Mongo: if fields exist at all (with empty value) or if its not
        return "redirect:/repairlist";
    }

    @PostMapping(value = "/createRepairRequest", params = {"generateCertificate"})
    public String generateCertificate(
            @Valid RepairRequest repairRequest, @RequestParam Map<String, String> form,
            BindingResult bindingResult, Model model) {
        requestValidator.validate(repairRequest, bindingResult);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getFieldErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("requestDraft", repairRequest);
            return "createRepairRequest";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        certificatePath = excelService.generateAcceptanceCertificate(repairRequest, user, form);
        return "redirect:/downloads/xlsx";
    }

    @GetMapping("/downloads/xlsx")
    public void downloadCertificate(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        File file = new File(certificatePath);

        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            httpResponse.setContentType(mimeType);

            httpResponse.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() +
                    "\"");
            httpResponse.setContentLength((int) file.length());
            try {
                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                FileCopyUtils.copy(inputStream, httpResponse.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
