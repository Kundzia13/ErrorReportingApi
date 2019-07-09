package pl.akademiakodu.reporting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.akademiakodu.reporting.model.Comment;
import pl.akademiakodu.reporting.repository.CommentRepository;

@RequestMapping("/comments")
@Controller
public class CommentController {

    private CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }


    @PostMapping("")
    public String create(@ModelAttribute Comment comment, RedirectAttributes redirectAttributes){
        commentRepository.save(comment);
        redirectAttributes.addFlashAttribute("message","Added comment!");
        return "redirect:/reports/"+comment.getReport().getId();
        // redirect - protection against adding a new id after the page is refreshed
    }
}
