package com.groupwork.pharmacy.management.system.controllers;

import com.groupwork.pharmacy.management.system.Utility.Utility;
import com.groupwork.pharmacy.management.system.model.*;
import com.groupwork.pharmacy.management.system.model.confirmationTokens.UserConfirmationToken;
import com.groupwork.pharmacy.management.system.repositories.*;
import com.groupwork.pharmacy.management.system.repositories.confirmationTokens.UserConfirmationTokenRepository;
import com.groupwork.pharmacy.management.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private AdminRepository repository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private SuppliersRepository suppliersRepository;

    @Autowired

    private CustomerRepository customerRepository;

    @Autowired

    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAdminDetailsService customAdminDetailsService;

    @Autowired
    private CustomMedicineService customMedicineService;

    @Autowired
    private CustomSupplierDetailsService customSupplierDetailsService;

    @Autowired
    UserConfirmationTokenRepository userConfirmationTokenRepository;



    @Autowired
    JavaMailSender mailSender;

    @Autowired
   AuthenticationManager authenticationManager;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }
    //Online Shop


    //Register
    @GetMapping("/register")
    public String showSignUpForm(Model model, @ModelAttribute User user){
        model.addAttribute("user",new User());
        return "User/staff_registration";
    }

    @GetMapping("/LoginAdmin")
    public String showAdminLogin(@ModelAttribute Admin admin){
        return "admin/admin_login_page";
    }

    @GetMapping("/login")
    public String showStaffLogin(@ModelAttribute User user){return "User/staff_login_page";}

    @GetMapping("/sidebar")
    public String showSidebar(){
        return "layout";
    }


    @GetMapping("/LoginClerk")
    public String showClerkLogin(@ModelAttribute Clerk clerk){
        return "clerk_login_page";
    }

    @GetMapping("/LoginCustomer")
    public String showCustomerLogin(@ModelAttribute Customer customer){
        return "Store/OnlineLogin";
    }



    @GetMapping("/admin/register")
    public String showAdminSignUpForm(Model model, @ModelAttribute Admin admin){
        model.addAttribute("admin",new Admin());
        return "admin/admin_registration";
    }
    @PostMapping("/process_adminRegister")
    public String processAdminRegistration(@ModelAttribute Admin admin){
        if(repository.findByEmail(admin.getEmail()) == null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(admin.getPassword());
            admin.setPassword(encodedPassword);
            repository.save(admin);
            return "admin/admin_register_success";
        }
        else
            return "admin/errorMessage";
    }

    @GetMapping("/customer/register")
    public String showCustomerSignUpForm(Model model, @ModelAttribute Customer customer){
        model.addAttribute("customer",new Customer());
        return "Store/OnlineRegistration";
    }
    @PostMapping("/process_customerRegister")
    public String processCustomerRegistration(@ModelAttribute Customer customer){
        if(customerRepository.findByEmail(customer.getEmail()) == null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(customer.getPassword());
            customer.setPassword(encodedPassword);
            customerRepository.save(customer);
            return "Store/customer_register_success";
        }
        else
            return "Store/errorMessage";
    }

    @PostMapping("/process_addMedicine")
    public String processAddMedicine(@ModelAttribute Medicine medicine,Model model){
        if(medicineRepository.findByName(medicine.getName())==null){
            medicineRepository.save(medicine);
            model.addAttribute("message","Medicine successfully added");
        }
        else model.addAttribute("message","cannot add, Medicine Exist");
        return "admin/errorMessage";
    }


    private void sendEmail(String email, String activateAccountLink) throws UnsupportedEncodingException, MessagingException {
        MimeMessage message=mailSender.createMimeMessage();//we use mimeMessage bcoz we are sending an html email
        MimeMessageHelper helper=new MimeMessageHelper(message);

        helper.setFrom("jaredfwaya@outlook.com","Pharmacy Management Support");
        helper.setTo(email);

        String subject="Link to activate your account in Pharmacy Management System ";
        String content="<p> Hey,</p>"
                +"<p>You have requested to activate your account</p>"
                +"<p>Click the link below</p>"
                +"<p><b><a href=\"" + activateAccountLink + "\">Activate Account</a></b></p>"
                +"<p>Ignore this email if you have not attempted to activate your account</p>";
        helper.setSubject(subject);
        helper.setText(content,true);

        mailSender.send(message);
    }
    @PostMapping("/process_Register")
    public String processRegistration(@ModelAttribute User user,HttpServletRequest request, Model model, ModelAndView modelAndView){
        if(repo.findByEmail(user.getEmail())==null) {

          try {


              BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
              String encodedPassword = encoder.encode(user.getPassword());
              user.setPassword(encodedPassword);
              repo.save(user);

              UserConfirmationToken confirmationToken = new UserConfirmationToken(user);
              String token = confirmationToken.getConfirmationToken();
              String email = user.getEmail();
              userConfirmationTokenRepository.save(confirmationToken);

              String activateUserAccountLink = Utility.getSiteURL(request) + "/confirm-account?token=" + token;
              sendEmail(email, activateUserAccountLink);

           /* SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("jaredfwaya@outlook.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);*/
              model.addAttribute("title", "We have sent you a verification email,check your email");

              modelAndView.addObject("email", user.getEmail());

              modelAndView.setViewName("successful Registration");
          }
          catch ( UnsupportedEncodingException | MessagingException e) {
              model.addAttribute("error","error while sending email");
          }

          return "User/staff_register_success";

        }
        else
            return "User/errorMessage";


    }

    @GetMapping("/confirm-account")
    public ModelAndView confirmUserAccount(@RequestParam(value="token")String confirmationToken,ModelAndView modelAndView )
    {
        UserConfirmationToken confirmationToken1 =userConfirmationTokenRepository.findUserByConfirmationToken(confirmationToken);
        System.out.println("");

        if(confirmationToken1 == null)
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        else
        {
            User user = repo.findByEmail(confirmationToken1.getUser().getEmail());
            user.setEnabled(true);
            repo.save(user);
            modelAndView.addObject("message","Successful registration");
            modelAndView.setViewName("accountVerified");

        }
        return modelAndView;


    }
    @GetMapping("/admin/list_users")
    public String viewUsersList(Model model,@Param("keyword") String keyword, @ModelAttribute User user){
        List<User> listUsers= customUserDetailsService.listSearchedUser(keyword);
        model.addAttribute("listUsers",listUsers);
        model.addAttribute("keyword",keyword);
        return "admin/users";
    }

    @GetMapping("/admin/list_medicines")
    public String viewMedicinesList(Model model,@Param("keyword") String keyword, @ModelAttribute Medicine medicine){
        List<Medicine> listMedicines= customMedicineService.listSearchedMedicine(keyword);
        model.addAttribute("listMedicines",listMedicines);
        return "admin/medicine";
    }
    @GetMapping("/admin/list_admins")
    public String viewAdminList(Model model,@Param("keyword") String keyword, @ModelAttribute Admin admin){
        List<Admin> listAdmins=customAdminDetailsService.listSearchedAdmin(keyword);
        model.addAttribute("listAdmins",listAdmins);
        return "admin/admins";
    }

    @GetMapping("/admin/list_suppliers")
    public String viewSuppliersList(Model model,@Param("keyword") String keyword, @ModelAttribute Suppliers suppliers){
        List<Suppliers> listSuppliers=customSupplierDetailsService.listSearchedSupplier(keyword);
        model.addAttribute("listSuppliers",listSuppliers);
        return "admin/suppliers";
    }

    @GetMapping("/delete_medicine/{id}")
    public String deleteMedicineById(@PathVariable("id") Long id){
        medicineRepository.deleteById(id);
        return "redirect:/admin/list_medicines";
    }

    @GetMapping("/delete_supplier/{id}")
    public String deleteSuppliersById(@PathVariable("id")Long id) {
        suppliersRepository.deleteById(id);
        return "redirect:/admin/list_suppliers";
    }

    @GetMapping("/delete_user/{id}")
    public String deleteUserById(@PathVariable("id")Long id){
        repo.deleteById(id);
        return "redirect:/admin/list_users";
    }
    @GetMapping("/delete_admin/{id}")
    public String deleteAdminById(@PathVariable("id") Long id){
        repository.deleteById(id);
        return "redirect:/admin/list_admins";
    }
}
