package com.javainuse.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "project")
public class DAOProject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String projectName;
    private String projectDesc;
    private String gitUrl;
    private String projectStatus;

    @NotNull
    @ManyToOne
    private DAOUser user;

    public DAOUser getUser() {
        return user;
    }

    public void setUser(DAOUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }
}
