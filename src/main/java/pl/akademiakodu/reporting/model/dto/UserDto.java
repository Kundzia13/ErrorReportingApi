package pl.akademiakodu.reporting.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akademiakodu.reporting.model.entities.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {
    private int id;
    private String email;
    private String name;
    private int active;
    private Set<Role> roles;
    private Set<ReportDto> reports = new HashSet<ReportDto>();
}
