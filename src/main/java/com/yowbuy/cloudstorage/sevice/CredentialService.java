package com.yowbuy.cloudstorage.sevice;

import com.yowbuy.cloudstorage.mapper.CredentialMapper;
import com.yowbuy.cloudstorage.model.Credential;
import com.yowbuy.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }
    public int addCredential(Credential credential, String username){
        User user = userService.getUser(username);
        if(user == null){
            return 0;
        }
        credential.setUserId(user.getUserid());
        generateKey(credential);
        return credentialMapper.addCredential(credential);
    }
    public int deleteCredential(Integer id){
        return credentialMapper.deleteCredential(id);
    }
    public int updateCredential(Credential credential, String username){
        User user = userService.getUser(username);
        if(user == null){
            return 0;
        }
        credential.setUserId(user.getUserid());
        generateKey(credential);
        return credentialMapper.updateCredential(credential);
    }

    private void generateKey(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String hashedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(hashedPassword);
    }

    public Credential getCredential(Integer credentialId){
        return credentialMapper.getCredential(credentialId);
    }
    public List<Credential> getCredentialList(String username){
        User user = userService.getUser(username);
        if(user == null){
            return new ArrayList<>();
        }
        return credentialMapper.getCredentialList(user.getUserid());
    }
}
