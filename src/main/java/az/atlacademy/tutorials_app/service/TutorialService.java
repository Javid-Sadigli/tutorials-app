package az.atlacademy.tutorials_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import az.atlacademy.tutorials_app.exception.TutorialNotFoundException;
import az.atlacademy.tutorials_app.mapper.TutorialMapper;
import az.atlacademy.tutorials_app.model.dto.TutorialRequestDTO;
import az.atlacademy.tutorials_app.model.dto.TutorialResponseDTO;
import az.atlacademy.tutorials_app.model.entity.TutorialEntity;
import az.atlacademy.tutorials_app.model.response.SuccessResponse;
import az.atlacademy.tutorials_app.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TutorialService 
{
    private final TutorialRepository tutorialRepository; 
    private final TutorialMapper tutorialMapper; 

    public SuccessResponse<TutorialResponseDTO> createTutorial(TutorialRequestDTO tutorialDTO)
    {
        TutorialEntity entity = tutorialMapper.requestDTOToEntity(tutorialDTO); 
        entity = tutorialRepository.save(entity);
        log.info("Created tutorial: {}", entity);
        TutorialResponseDTO responseDTO = tutorialMapper.entityToResponseDTO(entity);
        return SuccessResponse
                .<TutorialResponseDTO>builder()
                .body(responseDTO)
                .statusCode(HttpStatus.CREATED.value())
                .message("Tutorial created successfully.")
                .build();
    }

    public SuccessResponse<TutorialResponseDTO> getTutorialById(Long id)
    {
        TutorialEntity entity = tutorialRepository.findById(id).orElseThrow(
            () -> {
                String message = "Tutorial not found with id : " + id; 
                log.info(message);
                return new TutorialNotFoundException(message);
            }
        );
        log.info("Found tutorial by id {} : {}", id, entity);
        TutorialResponseDTO dto = tutorialMapper.entityToResponseDTO(entity);
        return SuccessResponse
                .<TutorialResponseDTO>builder()
                .body(dto)
                .statusCode(HttpStatus.OK.value())
                .message("Tutorial found successfully.")
                .build();
    }

    public SuccessResponse<List<TutorialResponseDTO>> getAllTutorials(Boolean published)
    {
        if (published == null) 
        {
            log.info("Getting all tutorials");
            return SuccessResponse
                    .<List<TutorialResponseDTO>>builder()
                    .body(tutorialRepository.findAll()
                            .stream()
                            .map(this.tutorialMapper::entityToResponseDTO)
                            .collect(Collectors.toList()))
                    .statusCode(HttpStatus.OK.value())
                    .message("Tutorials found successfully.")
                    .build();    
        }
        else
        {
            log.info("Getting tutorials by published status: {}", published);
            return SuccessResponse
            .<List<TutorialResponseDTO>>builder()
            .body(tutorialRepository.findByPublished(published)
                    .stream()
                    .map(this.tutorialMapper::entityToResponseDTO)
                    .collect(Collectors.toList()))
            .statusCode(HttpStatus.OK.value())
            .message("Tutorials found successfully.")
            .build(); 
        }
    }

    public SuccessResponse<TutorialResponseDTO> updateTutorial(TutorialRequestDTO tutorialDTO, Long id)
    {
        TutorialEntity tutorialEntity = tutorialRepository.findById(id).orElseThrow(
            () -> {
                String message = "Tutorial not found with id : " + id;
                log.info(message);
                return new TutorialNotFoundException(message);
            }
        );
        this.tutorialMapper.convertRequestToEntity(tutorialDTO, tutorialEntity);
        tutorialEntity = tutorialRepository.save(tutorialEntity);
        log.info("Updated tutorial: {}", tutorialEntity);
        TutorialResponseDTO responseDTO = this.tutorialMapper.entityToResponseDTO(tutorialEntity); 
        return SuccessResponse
                .<TutorialResponseDTO>builder()
                .body(responseDTO)
                .statusCode(HttpStatus.OK.value())
                .message("Tutorial updated successfully.")
                .build();
    }

    public void deleteTutorialById(Long id)
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
