package az.atlacademy.tutorials_app.model.dto;

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
public class TutorialResponseDTO 
{
    private int id; 
    private String title; 
    private String description; 
    private boolean published; 
}
