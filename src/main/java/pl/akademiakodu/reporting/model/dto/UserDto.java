package pl.akademiakodu.reporting.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private int id;
    private String email;
    private String name;
    private String lastName;
    private int active;
}