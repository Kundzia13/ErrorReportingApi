package pl.akademiakodu.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.akademiakodu.reporting.model.entities.Comment;
import pl.akademiakodu.reporting.model.entities.Report;
import pl.akademiakodu.reporting.repository.ReportRepository;

@RequestMapping("/reports")
@Controller
public class ReportController {

    private ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @RequestMapping(path = "/new",method = RequestMethod.GET)
    public String addReport(ModelMap modelMap) {
      /*  List<User> userList = new ArrayList<>();
        modelMap.addAttribute("users", userList);*/
        modelMap.put("report", new Report());
        return "reports/new";
    }

    @PostMapping("/new")
    public String createReport(@ModelAttribute Report report) {
        Report newReport = new Report();
        newReport.setReportTitle(report.getReportTitle());
        newReport.setStatus(report.getStatus());
        newReport.setContent(report.getContent());
        reportRepository.save(newReport);

      /*  Set<User> userList = new HashSet<>();
        report.setUsers(userList);*/
        return "redirect:/reports/" + newReport.getId();
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
