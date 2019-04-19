package com.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtil {

    private FileUtil fileUtil = new FileUtil();

    private FileUtil(){

    }

    public FileUtil getFileUtil(){
        return fileUtil;
    }

    /**
     * 文件上传的方法
     */
    public static String saveFile(MultipartFile multipartFile, HttpServletRequest request){
        String originalName=multipartFile.getOriginalFilename();
        String realPath = request.getServletContext().getRealPath("/Headimages");
        String path="E:\\graduationPic\\";
        String uuidName = UUID.randomUUID().toString().substring(0, 4);

        StringBuilder concatName = new StringBuilder(originalName);

        StringBuilder lastName = concatName.insert(originalName.lastIndexOf("."), uuidName);
        //上传的路径 File.separator 分隔符
        String uploadPath = realPath + File.separator + lastName;
        File file=new File(realPath);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            multipartFile.transferTo(new File(uploadPath));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadPath;
    }
}
