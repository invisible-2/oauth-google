package com.iongroup.api.oauth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    private String email;

    public UserDto(String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }
}
