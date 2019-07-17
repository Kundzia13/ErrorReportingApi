package pl.akademiakodu.reporting.service;

import pl.akademiakodu.reporting.model.dto.CommentDto;
import pl.akademiakodu.reporting.model.dto.ReportDto;
import pl.akademiakodu.reporting.model.dto.UserDto;
import pl.akademiakodu.reporting.model.entities.Comment;
import pl.akademiakodu.reporting.model.entities.Report;
import pl.akademiakodu.reporting.model.entities.User;

public class Service {

    public static ReportDto toDto(Report model) {
        ReportDto dto = new ReportDto();
        dto.setId(model.getId());
        dto.setReportTitle(model.getReportTitle());
        dto.setContent(model.getContent());
        return dto;
    }

    public Report toModel(ReportDto dto) {
        Report model = new Report();
        model.setId(dto.getId());
        model.setReportTitle(dto.getReportTitle());
        model.setContent(dto.getContent());
        return model;
    }

    public static CommentDto toDto(Comment model) {
        CommentDto dto = new CommentDto();
        dto.setId(model.getId());
        dto.setDescription(model.getDescription());
        return dto;
    }

    public static Comment toModel(CommentDto dto) {
        Comment model = new Comment();
        model.setId(dto.getId());
        model.setDescription(dto.getDescription());
        return model;
    }
    public static UserDto toDto(User model){
        UserDto dto = new UserDto();
        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());
        dto.setLastName(model.getLastName());
        dto.setActive(model.getActive());
        return dto;
    }
    private static User toModel(UserDto dto) {
        User model = new User();
        model.setId(dto.getId());
        model.setEmail(dto.getEmail());
        model.setName(dto.getName());
        model.setLastName(dto.getLastName());
        model.setActive(dto.getActive());
        return model;
    }
}
