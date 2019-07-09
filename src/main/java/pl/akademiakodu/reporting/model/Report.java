package pl.akademiakodu.reporting.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reportTitle;
    private Enum status;
    private String content;
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
