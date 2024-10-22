package az.atlacademy.tutorials_app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialResponseDTO implements Serializable 
{
    private Long id; 
    private String title; 
    private String description; 
    private boolean published; 
    private double price;
}
