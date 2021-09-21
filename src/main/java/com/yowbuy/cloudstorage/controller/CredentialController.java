package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.model.Credential;
import com.yowbuy.cloudstorage.sevice.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String addCredential(Credential credential, Authentication authentication){
        int row = 0;
        if(credential.getCredentialId() == null){
            row = credentialService.addCredential(credential, authentication.getName());
        } else {
            row = credentialService.updateCredential(credential, authentication.getName());
        }
        return "redirect:/home";
    }

    @GetMapping("/deleteCredential")
    public String deleteCredential(@RequestParam("id") Integer credentialId, Model model, Authentication authentication){
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }
}
