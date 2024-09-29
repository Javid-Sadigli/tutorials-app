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

import az.atlacademy.tutorials_app.model.dto.TutorialRequestDTO;
import az.atlacademy.tutorials_app.model.dto.TutorialResponseDTO;
import az.atlacademy.tutorials_app.service.TutorialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tutorial")
public class TutorialController 
{
    private final TutorialService tutorialService; 

    @GetMapping("/{id}")
    public ResponseEntity<TutorialResponseDTO> getTutorialById(@PathVariable int id)
    {
        log.info("GET /api/tutorial/{}", id);
        try
        {
            TutorialResponseDTO dto = tutorialService.getTutorialById(id); 
            if (dto == null) 
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
            }
            else 
            {
                return ResponseEntity.ok(dto);
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<TutorialResponseDTO> createTutorial(@RequestBody TutorialRequestDTO tutorial)
    {
        log.info("POST /api/tutorial");
        try
        {
            TutorialResponseDTO dto = tutorialService.createTutorial(tutorial); 
            if (dto == null) 
            {
                return ResponseEntity.badRequest().body(null);     
            }
            else 
            {
                return ResponseEntity.status(HttpStatus.CREATED).body(dto);
            }
        } 
        catch(Exception e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<TutorialResponseDTO>> getAllTutorials(@RequestParam(required = false) Boolean published)
    {
        log.info("GET /api/tutorial");
        try
        {
            return ResponseEntity.ok(tutorialService.getAllTutorials(published)); 
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutorialResponseDTO> updateTutorial(@RequestBody TutorialRequestDTO tutorial, @PathVariable int id)
    {
        log.info("PUT /api/tutorial/{}", id);
        try
        {
            TutorialResponseDTO updatedDTO = tutorialService.updateTutorial(tutorial, id);
            if (updatedDTO == null) 
            {
                return ResponseEntity.badRequest().body(null);
            }
            else 
            {
                return ResponseEntity.ok(updatedDTO);
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable int id)
    {
        log.info("DELETE /api/tutorial/{}", id);
        try
        {
            tutorialService.deleteTutorialById(id);
            return ResponseEntity.ok("Tutorial deleted successfully");
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTutorials()
    {
        log.info("DELETE /api/tutorial");
        try
        {
            tutorialService.deleteAllTutorials();
            return ResponseEntity.ok("All tutorials deleted successfully");
        }
        catch(Exception e)
        {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
