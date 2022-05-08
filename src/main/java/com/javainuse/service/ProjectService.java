package com.javainuse.service;

import com.javainuse.Constants;
import com.javainuse.dto.ProjectDTO;
import com.javainuse.model.DAOProject;
import com.javainuse.model.DAOUser;
import com.javainuse.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProjectService {

    @Autowired
    ProjectRepo projectRepo;

    @Autowired
    UserService userService;

    public DAOProject save(ProjectDTO projectDTO) {
        DAOProject newProject  = new DAOProject();
       newProject.setProjectDesc(projectDTO.getProjectDesc());
       newProject.setProjectName(projectDTO.getProjectName());
       newProject.setProjectStatus(projectDTO.getProjectStatus().toLowerCase());
       newProject.setGitUrl(projectDTO.getGitUrl());
        newProject.setUser(userService.getUserByUserName(getUserName()));
        return projectRepo.save(newProject);
    }

    public List<DAOProject> getProjects(String userName) {
        String currentUser = getUserName();
        if(userName.equals(currentUser)){
            DAOUser user = userService.getUserByUserName(currentUser);
            return getOwnProject(user);
        }else{
            DAOUser user = userService.getUserByUserName(userName);
            return getOtherPublicProject(user);
        }
    }

    public String deleteProjectById(Long projectId){
        Optional<DAOProject> projectDTO = projectRepo.findById(projectId);
        DAOUser user = userService.getUserByUserName(getUserName());
        if(projectDTO.isPresent()&&projectDTO.get().getUser().getId() == user.getId()){
            projectRepo.deleteById(projectId);
            return "project "+projectId+" deleted successfully";
        }else{
            return "You are not authorised to delete this project";
        }
    }

    public DAOProject updateProjectById(ProjectDTO project) throws Exception {
        Optional<DAOProject> projectDTO = projectRepo.findById(project.getId());
        DAOUser user = userService.getUserByUserName(getUserName());
        if(projectDTO.isPresent()&&projectDTO.get().getUser().getId() == user.getId()){
            DAOProject updatedProject  = projectDTO.get();
            updateProjectDTO(updatedProject,project);
            return projectRepo.save(updatedProject);
        }else{
            throw new Exception("Project not found or you not authorised to update this project");
        }
    }

    private void updateProjectDTO(DAOProject projectDTO, ProjectDTO project) {
        if(project.getProjectStatus()!=null){
            projectDTO.setProjectStatus(project.getProjectStatus());
        }
        if(project.getProjectDesc()!=null){
            projectDTO.setProjectDesc(project.getProjectDesc());
        }
        if(project.getGitUrl()!=null){
            projectDTO.setGitUrl(project.getGitUrl());
        }
        if(project.getProjectName()!=null){
            projectDTO.setProjectName(project.getProjectName());
        }
    }

    private List<DAOProject> getOtherPublicProject(DAOUser user){
        return projectRepo.getOtherPublicProject(user.getId(), Constants.PUBLIC_PROJECT);
    }

    private List<DAOProject> getOwnProject(DAOUser user){
        return projectRepo.getOwnProject(user.getId());
    }

    private static String  getUserName(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
         return userDetails.getUsername();
    }



}
