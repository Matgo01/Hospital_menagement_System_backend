package com.example.Hospital_menagement_System.controller;

import com.example.Hospital_menagement_System.model.MailStructure;
import com.example.Hospital_menagement_System.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail,mailStructure);
        return "email send";
    }
}
