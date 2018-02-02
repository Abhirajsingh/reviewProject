package com.zemoso.project.utils;

import com.zemoso.project.exception.FileException;
import com.zemoso.project.service.FileSaveServiceImp;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;

@Component
public class FileSaveMapper {

    public Map<String, Object> getObjectMap(MultipartFile file,String picDirectory) throws FileException{
        try{
        Map<String , Object> map = new HashMap<>();
        map.put("filePath" , new FileSaveServiceImp().saveFileAndGetPath(file,picDirectory));
        map.put("id" , CompanyUtil.getCompanyId());
        return map;}
        catch (Exception e){
            throw new FileException("FileException , /FileSaveMapper/getObjectMap" , e);
        }
    }
}
