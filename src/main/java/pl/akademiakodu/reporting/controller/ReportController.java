package pl.akademiakodu.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String addReport(Model model) {
      /*  List<User> userList = new ArrayList<>();
        modelMap.addAttribute("users", userList);*/
        //  modelMap.put("report", new Report());
        model.addAttribute("status", Status.values());
        return "reports/new";
    }

    @PostMapping("/new")
    public String createReport(@RequestParam(value = "title") String titleParam,
                               @RequestParam(value = "status") Status status,
                               @RequestParam(value = "content") String content,
                               Model model) {
        List<Report> reportsList = new ArrayList<>();
        Iterable<Report> reportIterable = reportRepository.findAll();
        for (Report report : reportIterable) {
            reportsList.add(report);
        }
        model.addAttribute("reports", reportsList);
      /*  Set<User> userList = new HashSet<>();
        report.setUsers(userList);*/
        Report report = new Report(titleParam, status, content);
        reportRepository.save(report);
        return "redirect:/reports/" + report.getId();
        // redirect - protection against adding a new id after the page is refreshed
    }

    @GetMapping("/{id}")
    public String showReport(@PathVariable String id, Model model) {
        Report report = reportRepository.findById(Integer.valueOf(id)).get();
        model.addAttribute("report", report);
        Comment comment = new Comment();
        comment.setReport(report);
        model.addAttribute("comment", comment);
        model.addAttribute("comments", report.getComments());
        return "reports/show";
    }

    @RequestMapping("/browse")
    public ModelAndView browse(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Report> reportsList = new ArrayList<>();
        Iterable<Report> reportIterable = reportRepository.findAll();
        for (Report report : reportIterable) {
            reportsList.add(report);
        }
        model.addAttribute("reports", reportsList);
        modelAndView.setViewName("reports/browse");
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reports/search");
        return modelAndView;
    }

}
