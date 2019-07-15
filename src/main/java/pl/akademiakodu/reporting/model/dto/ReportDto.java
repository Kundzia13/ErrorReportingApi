package pl.akademiakodu.reporting.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ReportDto {
   private Integer id;
    private String reportTitle;
    private String status;
    private String content;
    private List<CommentDto> comments = new ArrayList<>();
    private Set<UserDto> users = new HashSet<>();
}
