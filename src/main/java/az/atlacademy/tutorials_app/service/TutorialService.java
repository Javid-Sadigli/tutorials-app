package az.atlacademy.tutorials_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import az.atlacademy.tutorials_app.model.entity.TutorialEntity;
import az.atlacademy.tutorials_app.repository.impl.TutorialRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorialService 
{
    private final TutorialRepository tutorialRepository; 

    public TutorialEntity createTutorial(TutorialEntity tutorialEntity)
    {
        return tutorialRepository.save(tutorialEntity);
    }

    public TutorialEntity getTutorialById(int id)
    {
        return tutorialRepository.findById(id);
    }

    public List<TutorialEntity> getAllTutorials(Boolean published)
    {
        if (published == null) 
        {
            return tutorialRepository.findAll();    
        }
        else
        {
            return tutorialRepository.findByPublished(published); 
        }
    }

    public TutorialEntity updateTutorial(TutorialEntity tutorialEntity, int id)
    {
        tutorialEntity.setId(id);
        return tutorialRepository.save(tutorialEntity);
    }

    public void deleteTutorialById(int id)
    {
        tutorialRepository.deleteById(id);
    }

    public void deleteAllTutorials()
    {
        tutorialRepository.deleteAll();
    }
}
