package com.javainuse.repo;

import com.javainuse.model.DAOProject;
import com.javainuse.model.DAOUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends CrudRepository<DAOProject, Long> {

    @Query(value = "SELECT * FROM project p WHERE p.user_id = ?1 && project_status = ?2", nativeQuery = true)
    List<DAOProject> getOtherPublicProject(Long user, String projectStatus);


    @Query(value = "SELECT * FROM project p WHERE p.user_id = ?1", nativeQuery = true)
    List<DAOProject> getOwnProject(Long user);

}
