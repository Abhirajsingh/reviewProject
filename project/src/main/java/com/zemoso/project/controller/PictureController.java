package com.zemoso.project.controller;


import com.zemoso.project.exception.FileException;
import com.zemoso.project.utils.FileSaveMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class PictureController {

    @Value("${pictureDirectory}")
    private String picDirectory;

    /**
     * save the file in folder and send the path in response;
     *
     * @param file
     * @return <Map<String , Map<String >>
     */
    @RequestMapping(value = "/api/files", method = RequestMethod.POST)
    public ResponseEntity addPictureToDb
    (@RequestParam("file[attachment]") MultipartFile file) {
        Map<String, Object> map = null;
        try {
            map = new FileSaveMapper().getObjectMap(file, picDirectory);
            Map<String, Map<String, Object>> responseMap = new HashMap<>();
            responseMap.put("file", map);
            return ResponseEntity.status(HttpStatus.OK).body(responseMap);
        } catch (FileException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    /**
     * Request for Providing images from local System
     *
     * @param imageName
     * @param response
     * @throws IOException
     */
    @GetMapping(
            value = "/{imageName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public void getImageWithMediaType(@PathVariable String imageName,
                                      HttpServletResponse response) throws IOException {

        File initialFile = new File(picDirectory + imageName);
        try (InputStream is = new FileInputStream(initialFile)) {
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(is, response.getOutputStream());
        }
    }


}
