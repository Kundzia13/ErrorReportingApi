package pl.akademiakodu.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.reporting.model.Comment;
import pl.akademiakodu.reporting.model.Report;
import pl.akademiakodu.reporting.repository.ReportRepository;

@RequestMapping("/reports")
@Controller
public class ReportController {

    private ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @RequestMapping("/new")
    public String addReport(ModelMap modelMap) {
        modelMap.put("report", new Report());
        return "reports/new";
    }
    @PostMapping("")
    public String createReport(@ModelAttribute Report report) {
        reportRepository.save(report);
        return "redirect:/reports/" + report.getId();
        // redirect - protection against adding a new id after the page is refreshed
    }
    @GetMapping("/{id}")
    public String showReport(@PathVariable Integer id, ModelMap modelMap) {
        Report report = reportRepository.findById(id).get();
        modelMap.put("report", report);
        Comment comment = new Comment();
        comment.setReport(report);
        modelMap.put("comment", comment);
        modelMap.put("comments", report.getComments());
        return "reports/show";
    }
}
