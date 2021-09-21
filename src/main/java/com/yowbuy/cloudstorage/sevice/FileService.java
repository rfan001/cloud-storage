package com.yowbuy.cloudstorage.sevice;

import com.yowbuy.cloudstorage.mapper.FileMapper;
import com.yowbuy.cloudstorage.model.UploadedFile;
import com.yowbuy.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;
    private final UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public int uploadFile(MultipartFile file, String username) throws IOException {

        User user = userService.getUser(username);
        if(user == null){
            return 0;
        }
        UploadedFile newFile = new UploadedFile();
        newFile.setContentType(file.getContentType());
        newFile.setFilename(file.getOriginalFilename());
        newFile.setUserid(user.getUserid());
        newFile.setFilesize(""+file.getSize());
        newFile.setFileData(input2byte(file.getInputStream()));
        return fileMapper.uploadFile(newFile);
    }
    public byte[] input2byte(InputStream inStream) throws IOException {
        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = inStream.read(buff, 0, 1024)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            return swapStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<UploadedFile> getFiles(){
        return fileMapper.getFiles();
    }
    public void getFile(HttpServletRequest request, HttpServletResponse response, Integer fileId) throws UnsupportedEncodingException {
        downFileByBlob(request, response, fileId);
    }
    public String downFileByBlob(HttpServletRequest request, HttpServletResponse response, Integer fileId) throws UnsupportedEncodingException {
        UploadedFile uploadedFile = null;
        uploadedFile = fileMapper.getFile(fileId);
        if(uploadedFile!=null){
            String filename = uploadedFile.getFilename();
            String useAgent = request.getHeader("user-agent").toLowerCase();
            if(useAgent.contains("mise") || useAgent.contains("like gecko")){
                filename = URLEncoder.encode(filename,"UTF-8");
            } else {
                filename = new String(filename.getBytes(StandardCharsets.UTF_8), "iso-8859-1");
            }
            try {
                response.reset();
                byte[] fileStream = uploadedFile.getFileData();
                response.setContentType("application/x-msdownload");
                response.setCharacterEncoding("UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename="+filename);
                OutputStream toClient = new DataOutputStream(response.getOutputStream());
                toClient.write(fileStream);

                toClient.flush();
                toClient.close();
                return "Success";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Failed";
    }

    public int deleteFile(Integer fileId){
        return fileMapper.deleteFile(fileId);
    }

    public boolean filenameIsAvailable(String filename){
        UploadedFile uploadedFile = fileMapper.getFileByName(filename);
        if(uploadedFile!=null) return false;
        return true;
    }
}
