package com.zemoso.project.utils;

import com.zemoso.project.exception.FileException;
import com.zemoso.project.service.FileSaveServiceImp;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class FileSaveMapper {

    private String filePath = "demo";

    public Map<String, Object> getObjectMap(MultipartFile file,String picDirectory) throws FileException{
        try{
        Map<String , Object> map = new HashMap<>();
        filePath = new FileSaveServiceImp().saveFileAndGetPath(file,picDirectory);
        map.put("filePath" ,filePath );
        map.put("id" , UUID.randomUUID());
        return map;}
        catch (Exception e){
            throw new FileException("FileException , /FileSaveMapper/getObjectMap" , e);
        }
    }
    public String getFilePath(){
        return this.filePath;
    }
}
