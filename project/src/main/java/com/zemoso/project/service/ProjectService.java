package com.zemoso.project.service;

import com.zemoso.project.exception.DbException;
import com.zemoso.project.model.Project;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    /**
     * get
     * all project of a company;
     *
     * @param companyId
     * @return
     */
    List<Project> getAllProject(Long companyId) throws DbException;
    /**
     * get one Project search by ProjectId
     *
     * @param projectId
     * @return
     */
    Project getProject(Long projectId) throws DbException;
}
