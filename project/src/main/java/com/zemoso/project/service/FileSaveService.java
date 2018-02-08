package com.zemoso.project.service;

import com.zemoso.project.exception.FileException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileSaveService {

    String saveFileAndGetPath(MultipartFile file, String picDirectory) throws FileException;
}
