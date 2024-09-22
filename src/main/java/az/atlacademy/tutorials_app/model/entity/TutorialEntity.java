package az.atlacademy.tutorials_app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TutorialEntity 
{
    private int id; 
    private String title; 
    private String description; 
    private boolean published; 

    public TutorialEntity(String title, String description, boolean published)
    {
        this.title = title;
        this.description = description;
        this.published = published;
    }
    
}
