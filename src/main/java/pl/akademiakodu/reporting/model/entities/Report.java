package pl.akademiakodu.reporting.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer id;
    private String reportTitle;
    private Status status; //Enum
    private String content;
    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "report_user",
            joinColumns = { @JoinColumn(name = "report_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> users = new HashSet<>();

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", content='" + content + '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
