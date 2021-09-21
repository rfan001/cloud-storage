package com.yowbuy.cloudstorage.mapper;

import com.yowbuy.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    Credential getCredential(Integer credentialId);
    @Insert("INSERT INTO CREDENTIALS(url, username, `key`, password, userid) VALUES(#{url}, #{username}, #{key}, #{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, `key` = #{key}, password = #{password} WHERE credentialid = #{credentialId} and userId = #{userId}")
    int updateCredential(Credential credential);
    @Delete("DELETE FROM CREDENTIALS WHERE credentialid=#{credentialId}")
    int deleteCredential(Integer credentialId);
    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getCredentialList(Integer userId);
}
