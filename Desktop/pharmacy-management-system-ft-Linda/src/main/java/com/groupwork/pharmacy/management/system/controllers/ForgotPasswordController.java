package com.groupwork.pharmacy.management.system.controllers;

import com.groupwork.pharmacy.management.system.Utility.Utility;
import com.groupwork.pharmacy.management.system.model.Admin;
import com.groupwork.pharmacy.management.system.model.User;
import com.groupwork.pharmacy.management.system.service.CustomAdminDetailsService;
import com.groupwork.pharmacy.management.system.service.CustomUserDetailsService;
import com.groupwork.pharmacy.management.system.service.UserNotFoundException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ForgotPasswordController {
    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    CustomAdminDetailsService adminDetailsService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot_staff_password")
    public String showStaffForgotPasswordForm(Model model){
        model.addAttribute("Title","Forgot Password");
        return "staff_forgot_password_form";
    }

    @PostMapping ("/forgot_staff_password")
    public String processStaffForgotPasswordForm(HttpServletRequest request, Model model){
        String email=request.getParameter("email");
        String token=RandomString.make(45);

        try {
            userDetailsService.updateResetPasswordToken(token,email);

            //generate reset password link based on the token
            String resetPasswordLink= Utility.getSiteURL(request) + "/reset_staff_password?token=" + token;
            System.out.println(resetPasswordLink);
            //send token to email
            sendEmail(email,resetPasswordLink);
            model.addAttribute("message","We have sent you an email to reset your password,Please check");
        } catch (UserNotFoundException  e) {
          model.addAttribute("exception",e.getMessage());
        }
         catch ( UnsupportedEncodingException | MessagingException e) {
        model.addAttribute("error","error while sending email");
         }
        model.addAttribute("title","Forgot Password");
        return "staff_forgot_password_form";
    }

    private void sendEmail(String email, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message=mailSender.createMimeMessage();//we use mimeMessage bcoz we are sending an html email
        MimeMessageHelper helper=new MimeMessageHelper(message);

        helper.setFrom("jaredfwaya@outlook.com","Pharmacy Management Support");
        helper.setTo(email);

        String subject="Link to reset your password in Pharmacy Management System ";
        String content="<p> Hey,</p>"
                +"<p>You have requested to change your password</p>"
                +"<p>Click the link below</p>"
                +"<p><b><a href=\"" + resetPasswordLink + "\">Change Password</a></b></p>"
                +"<p>Ignore this email if you have not attempted to change your password</p>";
        helper.setSubject(subject);
        helper.setText(content,true);

        mailSender.send(message);
    }

    @GetMapping("/reset_staff_password")
    public String showResetPasswordForm(@Param(value="token") String token,Model model){

        //check if token is valid
        User user= userDetailsService.get(token);
        if(user==null){
            model.addAttribute("message","Invalid Token");
            return "errorMessage";
        }
        model.addAttribute("token",token);
        model.addAttribute("title","Reset your password");

        return "staff_reset_password_form";
    }

    @PostMapping("/reset_staff_password")
    public String handleResetPasswordForm(HttpServletRequest request,Model model){
        String token= request.getParameter("token");
        String password=request.getParameter("password");

        User user= userDetailsService.get(token);
        if(user==null){
            model.addAttribute("message","Invalid Token");
            return "errorMessage";

        }else {
            userDetailsService.updatePassword(user,password);
            model.addAttribute("message","You have changed the password successfully");
            return "register_success";
        }


    }





    @GetMapping("/forgot_admin_password")
    public String showAdminForgotPasswordForm(Model model){
        model.addAttribute("Title","Forgot Password");
        return "admin_forgot_password_form";
    }

    @PostMapping ("/forgot_admin_password")
    public String processAdminForgotPasswordForm(HttpServletRequest request, Model model){
        String email=request.getParameter("email");
        String token=RandomString.make(45);

        try {
            adminDetailsService.updateResetPasswordToken(token,email);

            //generate reset password link based on the token
            String resetPasswordLink= Utility.getSiteURL(request) + "/reset_admin_password?token=" + token;
            System.out.println(resetPasswordLink);
            //send token to email
            sendEmail(email,resetPasswordLink);
            model.addAttribute("message","We have sent you an email to reset your password,Please check");
        } catch (UserNotFoundException  e) {
            model.addAttribute("exception",e.getMessage());
        }
        catch ( UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error","error while sending email");
        }
        model.addAttribute("title","Forgot Password");
        return "admin_forgot_password_form";
    }


    @GetMapping("/reset_admin_password")
    public String showAdminResetPasswordForm(@Param(value="token") String token,Model model){

        //check if token is valid
       Admin admin= adminDetailsService.get(token);
        if(admin==null){
            model.addAttribute("message","Invalid Token");
            return "errorMessage";
        }
        model.addAttribute("token",token);
        model.addAttribute("title","Reset your password");

        return "admin_reset_password_form";
    }

    @PostMapping("/reset_admin_password")
    public String processResetPasswordForm(HttpServletRequest request,Model model){
        String token= request.getParameter("token");
        String password=request.getParameter("password");

        Admin admin= adminDetailsService.get(token);
        if(admin==null){
            model.addAttribute("message","Invalid Token");
            return "errorMessage";

        }else {
           adminDetailsService.updatePassword(admin,password);
            model.addAttribute("message","You have changed your password successfully");
            return "admin_register_success";
        }


    }




}
