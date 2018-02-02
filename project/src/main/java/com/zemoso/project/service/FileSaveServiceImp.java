package com.zemoso.project.service;

import com.zemoso.project.exception.FileException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@Qualifier("FileSaveServiceImp")
public class FileSaveServiceImp implements FileSaveService{
    /**
     * save file in given directory and return the path;
     * @param file
     * @param picDirectory
     * @return
     * @throws FileException
     */
    public String saveFileAndGetPath(MultipartFile file,String picDirectory) throws FileException {
        String uniqueId = UUID.randomUUID().toString();
        String filepath = picDirectory + uniqueId;
        try {
            File targetFile = new File(filepath);
            Files.copy(file.getInputStream(), targetFile.toPath());
        } catch (IOException e) {
            throw new FileException("Exception , /service/saveFileAndGetPath", e);
        }
        return uniqueId;
    }
}