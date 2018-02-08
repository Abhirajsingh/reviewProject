package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Project;
import com.zemoso.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("ProjectServiceImp")
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    /**
     * get
     * all project of a company;
     *
     * @param companyId
     * @return
     */
    public List<Project> getAllProject(Long companyId) throws DbException {
        try {
            return projectRepository.findAllByCompanyId(companyId);
        } catch (Exception e) {
            throw new DbException("Exception , /service/ProjectService/getAllProject", e);
        }
    }

    /**
     * get one Project search by ProjectId
     *
     * @param projectId
     * @return
     */
    public Project getProject(Long projectId) throws DbException {
        try {
            return projectRepository.findOne(projectId);
        } catch (Exception e) {
            throw new DbException("Exception , service/ProjectService/getProject", e);
        }
    }

}
