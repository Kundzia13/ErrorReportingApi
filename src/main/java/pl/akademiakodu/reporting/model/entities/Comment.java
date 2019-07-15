package pl.akademiakodu.reporting.model.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Report report;

    @Override
    public String toString() {
        return getDescription();
    }

}
