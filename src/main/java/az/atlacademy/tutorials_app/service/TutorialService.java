package az.atlacademy.tutorials_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import az.atlacademy.tutorials_app.mapper.TutorialMapper;
import az.atlacademy.tutorials_app.model.dto.TutorialRequestDTO;
import az.atlacademy.tutorials_app.model.dto.TutorialResponseDTO;
import az.atlacademy.tutorials_app.model.entity.TutorialEntity;
import az.atlacademy.tutorials_app.repository.impl.TutorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorialService 
{
    private final TutorialRepository tutorialRepository; 
    private final TutorialMapper tutorialMapper; 

    public TutorialResponseDTO createTutorial(TutorialRequestDTO tutorialDTO)
    {
        TutorialEntity entity = tutorialMapper.requestDTOToEntity(tutorialDTO); 
        entity = tutorialRepository.save(entity);
        log.info("Created tutorial: {}", entity);
        return tutorialMapper.entityToResponseDTO(entity);
    }

    public TutorialResponseDTO getTutorialById(int id)
    {
        TutorialEntity entity = tutorialRepository.findById(id);
        log.info("Found tutorial by id {} : {}", id, entity);
        return tutorialMapper.entityToResponseDTO(entity);
    }

    public List<TutorialResponseDTO> getAllTutorials(Boolean published)
    {
        if (published == null) 
        {
            log.info("Getting all tutorials");
            return tutorialRepository.findAll().stream().map(
                this.tutorialMapper::entityToResponseDTO
            ).collect(Collectors.toList());    
        }
        else
        {
            log.info("Getting tutorials by published status: {}", published);
            return tutorialRepository.findByPublished(published).stream().map(
                this.tutorialMapper::entityToResponseDTO
            ).collect(Collectors.toList()); 
        }
    }

    public TutorialResponseDTO updateTutorial(TutorialRequestDTO tutorialDTO, int id)
    {
        TutorialEntity tutorialEntity = tutorialMapper.requestDTOToEntity(tutorialDTO);
        tutorialEntity.setId(id);
        tutorialEntity = tutorialRepository.save(tutorialEntity);
        log.info("Updated tutorial: {}", tutorialEntity);
        return this.tutorialMapper.entityToResponseDTO(tutorialEntity); 
    }

    public void deleteTutorialById(int id)
    {
        log.info("Deleting tutorial by id: {}", id);
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials()
    {
        log.info("Deleting all tutorials");
        tutorialRepository.deleteAll();
    }
}
