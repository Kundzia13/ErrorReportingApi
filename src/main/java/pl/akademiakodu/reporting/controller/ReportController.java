package pl.akademiakodu.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.reporting.model.entities.Comment;
import pl.akademiakodu.reporting.model.entities.Report;
import pl.akademiakodu.reporting.model.entities.Status;
import pl.akademiakodu.reporting.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/reports")
@Controller
public class ReportController {

    private ReportRepository reportRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/new")
    public String addReport(ModelMap modelMap) {
      /*  List<User> userList = new ArrayList<>();
        modelMap.addAttribute("users", userList);*/
        modelMap.put("report", new Report());
        modelMap.addAttribute("status", Status.values());
        return "reports/new";
    }

    @PostMapping("/new")
    public String createReport(@ModelAttribute Report report,
                               Model model) {
        reportRepository.save(report);
        List<Report> reportList = new ArrayList<>();
        Iterable<Report> reportIterable = reportRepository.findAll();
        for (Report repo : reportIterable) {
            reportList.add(repo);
        }
        model.addAttribute("reports", reportList);
      /*  Set<User> userList = new HashSet<>();
        report.setUsers(userList);*/
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
    @GetMapping("/browse")
    public ModelAndView browse(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reports/browse");
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reports/search");
        return modelAndView;
    }

}
