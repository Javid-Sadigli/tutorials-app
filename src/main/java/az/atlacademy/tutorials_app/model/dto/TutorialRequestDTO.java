package az.atlacademy.tutorials_app.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialRequestDTO 
{
    @Size(min = 5, max = 30, message = "The size of the tutorial should have size between 5 and 30.")
    @NotBlank(message = "The title of the tutorial can not be blank.")
    private String title; 

    @Size(min = 20, max = 200, message = "The size of the tutorial description should have size between 10 and 200.")
    @NotBlank(message = "The description of the tutorial can not be blank.")
    private String description; 
    
    private boolean published; 

    @Max(value = 100, message = "The price of the tutorial can not be greater than 100$.")
    private double price; 
}
