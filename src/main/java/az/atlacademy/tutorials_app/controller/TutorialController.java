package az.atlacademy.tutorials_app.controller;

import java.util.List;

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
import az.atlacademy.tutorials_app.model.response.SuccessResponse;
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
    public ResponseEntity<SuccessResponse<TutorialResponseDTO>> getTutorialById(@PathVariable int id)
    {
        log.info("GET /api/tutorial/{}", id);
        SuccessResponse<TutorialResponseDTO> response = tutorialService.getTutorialById(id); 
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response); 
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<TutorialResponseDTO>> createTutorial(@RequestBody TutorialRequestDTO tutorial)
    {
        log.info("POST /api/tutorial");
        SuccessResponse<TutorialResponseDTO> response = tutorialService.createTutorial(tutorial);
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response);
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<TutorialResponseDTO>>> getAllTutorials(
        @RequestParam(required = false) Boolean published
    ){
        log.info("GET /api/tutorial");
        SuccessResponse<List<TutorialResponseDTO>> response = tutorialService.getAllTutorials(published); 
        return ResponseEntity 
                .status(response.getStatusCode())
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<TutorialResponseDTO>> updateTutorial(
        @RequestBody TutorialRequestDTO tutorial, 
        @PathVariable int id
    ){
        log.info("PUT /api/tutorial/{}", id);
        SuccessResponse<TutorialResponseDTO> response = tutorialService.updateTutorial(tutorial, id); 
        return ResponseEntity
                .status(response.getStatusCode())
                .body(response); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable int id)
    {
        log.info("DELETE /api/tutorial/{}", id);
        tutorialService.deleteTutorialById(id);
        return ResponseEntity.ok("Tutorial deleted successfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllTutorials()
    {
        log.info("DELETE /api/tutorial");
        tutorialService.deleteAllTutorials();
        return ResponseEntity.ok("All tutorials deleted successfully");
    }
}
