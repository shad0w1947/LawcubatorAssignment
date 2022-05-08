package com.javainuse.controller;

import com.javainuse.dto.ProjectDTO;
import com.javainuse.model.DAOProject;
import com.javainuse.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/addProject")
    public DAOProject addProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.save(projectDTO);
    }

    @GetMapping("/projects/{username}")
    public List<DAOProject> getPublicProjectByUserName(@PathVariable String username) {

        return projectService.getProjects(username);
    }

    @DeleteMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable Long id){
        return projectService.deleteProjectById(id);
    }

    @PostMapping("/updateProject")
    public DAOProject updateProject(@RequestBody ProjectDTO projectDTO) throws Exception {
        return projectService.updateProjectById(projectDTO);
    }

}
