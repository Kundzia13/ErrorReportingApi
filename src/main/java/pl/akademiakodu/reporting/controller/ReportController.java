package pl.akademiakodu.reporting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.reporting.model.entities.Comment;
import pl.akademiakodu.reporting.model.entities.Report;
import pl.akademiakodu.reporting.model.entities.Status;
import pl.akademiakodu.reporting.model.entities.User;
import pl.akademiakodu.reporting.repository.ReportRepository;
import pl.akademiakodu.reporting.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/reports")
@Controller
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    public ReportController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/new")
    public String addReport(Model model) {
        List<User> userList = new ArrayList<>();
        Iterable<User> userIterable = userRepository.findAll();
        for (User user : userIterable) {
            userList.add(user);
        }
        model.addAttribute("users", userList);
        model.addAttribute("status", Status.values());
        return "reports/new";
    }

    @PostMapping("/new")
    public String createReport(@RequestParam(value = "title") String titleParam,
                               @RequestParam(value = "status") Status status,
                               @RequestParam(value = "content") String content,
                               @RequestParam(value = "user") Integer[] userId,
                               Model model) {
        List<Report> reportsList = new ArrayList<>();
        Iterable<Report> reportIterable = reportRepository.findAll();
        for (Report report : reportIterable) {
            reportsList.add(report);
        }
        model.addAttribute("reports", reportsList);

        Report report = new Report(titleParam, status, content);

        Set<User> userList = new HashSet<>();
        for (Integer id : userId) {
            userList.add(userRepository.getOne(id));
        }

        report.setUsers(userList);

        reportRepository.save(report);
        return "redirect:/reports/" + report.getId();
        // redirect - protection against adding a new id after the page is refreshed
    }

    @GetMapping("/{id}")
    public String showReport(@PathVariable String id, Model model) {
        Report report = reportRepository.findById(Integer.valueOf(id)).get();
        model.addAttribute("report", report);
        model.addAttribute("users", report.getUsers());
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

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String searchTitle,
                               @RequestParam(value = "status") String status,
                               Model model) {
        ModelAndView modelAndView = new ModelAndView();
        List<Report> searchReportsList = new ArrayList<>();

        searchReportsList = reportRepository.findByStatus(Status.valueOf(status))
                .stream()
                .filter(e -> e.getReportTitle().matches("(?i)"+"(.*)" + searchTitle +"(.*)")
                ).collect(Collectors.toList());
        for (Report r:searchReportsList
             ) {
            System.out.println(r.toString());
        }
        model.addAttribute("search", searchReportsList);
        modelAndView.setViewName("reports/search");
        return modelAndView;
    }
}
