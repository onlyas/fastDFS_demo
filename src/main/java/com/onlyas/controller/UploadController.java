package com.onlyas.controller;

import com.onlyas.fastdfs.FastDFSClient;
import com.onlyas.fastdfs.FastDFSFile;
import com.onlyas.model.FileInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/file")
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping("/upload")
    public FileInfoVO upload(@RequestParam("file") MultipartFile uploadfile) {
        try {
            FileInfoVO fileInfoVO = saveFile(uploadfile);
            return fileInfoVO;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param multipartFile
     * @throws IOException
     * @return=
     */
    public FileInfoVO saveFile(MultipartFile multipartFile) throws IOException {
        FileInfoVO fileInfoVO = new FileInfoVO();
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            logger.error("upload file Exception!",e);
        }
        if (fileAbsolutePath == null) {
            logger.error("upload file failed,please upload again!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        fileInfoVO.setTrackerUrl(FastDFSClient.getTrackerUrl());
        fileInfoVO.setGroupName(fileAbsolutePath[0]);
        fileInfoVO.setRemoteFileName(fileAbsolutePath[1]);
        fileInfoVO.setUrl(path);
        return fileInfoVO;
    }
}
