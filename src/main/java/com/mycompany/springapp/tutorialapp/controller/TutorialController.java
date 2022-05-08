package com.mycompany.springapp.tutorialapp.controller;

import com.mycompany.springapp.tutorialapp.model.TutorialModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")//class level mapping
public class TutorialController {
    public List<TutorialModel> getAllTutorials()
    {
        return null;
    }
    public TutorialModel getTutorialById(long id)
    {
        return null;
    }
    public void deleteTutorial(long id)
    {

    }
    public void updateTutorial(TutorialModel tutorialModel)
    {

    }
    public void createTutorial(TutorialModel tutorialModel)
    {

    }
    @PostMapping(value = "/healthcheck")
    public ResponseEntity<TutorialModel> healthCheckPost(@RequestBody TutorialModel tutorialModel)
    {
        ResponseEntity<TutorialModel> responseEntity = new ResponseEntity<TutorialModel>(tutorialModel, HttpStatus.CREATED);
        return responseEntity;
        //return "healthy simple";
    }
    @PutMapping(value = "/healthcheck/{tutorialId}")
    public ResponseEntity<TutorialModel> healthCheckPut(@RequestBody TutorialModel tutorialModel, @PathVariable Long tutorialId)
    {
        ResponseEntity<TutorialModel> responseEntity = new ResponseEntity<TutorialModel>(tutorialModel, HttpStatus.CREATED);
        return responseEntity;
    }
    @GetMapping(value = "/healthcheck1")
    public String healthCheckSimple()
    {
        return "healthy simple";
    }
    @GetMapping(value = "/healthcheck")
    public String healthCheck(@RequestParam("id") Long xyzId
            ,@RequestParam("name") String xyzName,@RequestParam("title") String title)
    {
        return "healthy"+" id is "+xyzId+" Name is "+xyzName+" Title is "+title;
    }
    @GetMapping(value = "/healthcheck/{id}") //healthcheck/123
    public String healthCheckById(@PathVariable("id") Long xyzId)
    {
        System.out.println("id is "+xyzId);
        return "healthy + id is "+xyzId;
    }
    @GetMapping(value = "/healthcheck/{id}/{name}") //healthcheck/123/john
    public String healthCheckByIdandName(@PathVariable("id") Long xyzId,@PathVariable("name") String xyzName)
    {
        System.out.println("id is "+xyzId+ " name "+xyzName);
        return "healthy + id is "+xyzId+ " name "+xyzName;
    }
}
