package az.atlacademy.tutorials_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.atlacademy.tutorials_app.model.entity.TutorialEntity;
import az.atlacademy.tutorials_app.service.TutorialService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutorial")
public class TutorialController 
{
    private final TutorialService tutorialService; 

    @GetMapping("/{id}")
    public ResponseEntity<TutorialEntity> getTutorialById(@PathVariable int id)
    {
        try
        {
            TutorialEntity entity = tutorialService.getTutorialById(id); 
            if (entity == null) 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
            }
            else 
            {
                return ResponseEntity.ok(entity);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<TutorialEntity> createTutorial(@RequestBody TutorialEntity tutorial)
    {
        try
        {
            TutorialEntity entity = tutorialService.createTutorial(tutorial); 
            if (entity == null) 
            {
                return ResponseEntity.badRequest().body(null);     
            }
            else 
            {
                return ResponseEntity.status(HttpStatus.CREATED).body(entity);
            }
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<TutorialEntity>> getAllTutorials(@RequestParam(required = false) Boolean published)
    {
        try
        {
            return ResponseEntity.ok(tutorialService.getAllTutorials(published)); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorialEntity> updateTutorial(@RequestBody TutorialEntity tutorial, @PathVariable int id)
    {
        try
        {
            tutorial.setId(id);
            TutorialEntity updatedEntity = tutorialService.updateTutorial(tutorial, id);
            if (updatedEntity == null) 
            {
                return ResponseEntity.badRequest().body(null);
            }
            else 
            {
                return ResponseEntity.ok(updatedEntity);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable int id)
    {
        try
        {
            tutorialService.deleteTutorialById(id);
            return ResponseEntity.ok("Tutorial deleted successfully");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTutorials()
    {
        try
        {
            tutorialService.deleteAllTutorials();
            return ResponseEntity.ok("All tutorials deleted successfully");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
