package az.atlacademy.tutorials_app.mapper;

import org.springframework.stereotype.Component;

import az.atlacademy.tutorials_app.model.dto.TutorialRequestDTO;
import az.atlacademy.tutorials_app.model.dto.TutorialResponseDTO;
import az.atlacademy.tutorials_app.model.entity.TutorialEntity;

@Component
public class TutorialMapper 
{
    public TutorialResponseDTO entityToResponseDTO(TutorialEntity entity)
    {
        try
        {
            return TutorialResponseDTO.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .description(entity.getDescription())
                    .published(entity.isPublished())
                    .build();
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }

    public TutorialEntity requestDTOToEntity(TutorialRequestDTO dto)
    {
        try
        {
            return TutorialEntity.builder()
                    .title(dto.getTitle())
                    .description(dto.getDescription())
                    .published(dto.isPublished())
                    .build();
        }
        catch (NullPointerException e)
        {
            return null;
        }
    }

    public void convertRequestToEntity(TutorialRequestDTO dto, TutorialEntity entity)
    {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPublished(dto.isPublished());
    }
}
