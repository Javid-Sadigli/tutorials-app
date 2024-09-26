package az.atlacademy.tutorials_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public TutorialEntity createTutorial(TutorialEntity tutorialEntity)
    {
        log.info("Creating tutorial: {}", tutorialEntity);
        return tutorialRepository.save(tutorialEntity);
    }

    public TutorialEntity getTutorialById(int id)
    {
        log.info("Getting tutorial by id: {}", id);
        return tutorialRepository.findById(id);
    }

    public List<TutorialEntity> getAllTutorials(Boolean published)
    {
        if (published == null) 
        {
            log.info("Getting all tutorials");
            return tutorialRepository.findAll();    
        }
        else
        {
            log.info("Getting tutorials by published status: {}", published);
            return tutorialRepository.findByPublished(published); 
        }
    }

    public TutorialEntity updateTutorial(TutorialEntity tutorialEntity, int id)
    {
        log.info("Updating tutorial: {}", tutorialEntity);
        tutorialEntity.setId(id);
        return tutorialRepository.save(tutorialEntity);
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
