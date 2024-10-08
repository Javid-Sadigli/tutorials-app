package az.atlacademy.tutorials_app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import az.atlacademy.tutorials_app.model.dto.TutorialRequestDTO;
import az.atlacademy.tutorials_app.model.dto.TutorialResponseDTO;
import az.atlacademy.tutorials_app.model.entity.TutorialEntity;

@Mapper(componentModel = "spring")
public interface TutorialMapper 
{
    public TutorialEntity requestDTOToEntity(TutorialRequestDTO dto);
    public TutorialResponseDTO entityToResponseDTO(TutorialEntity entity);

    public void convertRequestToEntity(TutorialRequestDTO requestDTO, @MappingTarget TutorialEntity entity); 
}