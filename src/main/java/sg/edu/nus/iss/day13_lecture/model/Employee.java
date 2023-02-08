package sg.edu.nus.iss.day13_lecture.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @NotEmpty(message = "Please input a first name")
    @Size(min=1, max=100, message = "Please input a first name")
    private String firstName;

    @NotEmpty(message = "Please input a last name")
    @Size(min=1, max=100, message = "Please input a last name")
    private String lastName;

    @Email(message = "Invalid email format")
    @Size(max = 120)
    @Pattern(regexp = ".+@.+\\..+", message = "Wrong email format")
    private String email;

    @Pattern(regexp = "(\\8|9)[0-9]{7}", message="Invalid Phone format")
    private String phoneNumber;

    @Min(value = 800, message = "Min salary should be more than 800")
    private Integer salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Birth date cannot be greater than today")
    private Date birthday;
    private String address;
    
    @Digits(fraction=0, integer=6, message ="Postal code invalid")
    private Integer postalCode;
    
}
