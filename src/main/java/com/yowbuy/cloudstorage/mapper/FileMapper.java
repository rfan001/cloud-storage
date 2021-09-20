package com.yowbuy.cloudstorage.mapper;

import com.yowbuy.cloudstorage.model.UploadedFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Insert("INSERT INTO FILES(filename, contenttype, filesize, userid, filedata) " +
            "VALUES (#{filename}, #{contentType}, #{filesize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(UploadedFile file);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    UploadedFile getFile(Integer fileId);

    @Select("SELECT fileid, filename FROM FILES")
    List<UploadedFile> getFiles();

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int deleteFile(Integer fileId);
}
