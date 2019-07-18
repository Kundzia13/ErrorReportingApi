package pl.akademiakodu.reporting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor //will generate a constructor with no parameters
@AllArgsConstructor // generates a constructor with 1 parameter for each field in your class
public class ReportDto {
    private Integer id;
    private String reportTitle;
    private String content;
}
