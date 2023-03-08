package sg.edu.nus.iss.app.day13workshop.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.boot.ApplicationArguments;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.day13workshop.User;
import sg.edu.nus.iss.app.day13workshop.service.AppService;

@Controller
public class AppController {
    
    @Autowired
    AppService appService;

    @Autowired
    ApplicationArguments cliOpts;

    @GetMapping("/")
    public String renderForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/contact")
    public String submitHandler(Model model, @ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        try {
            appService.createUser(user, new File(cliOpts.getOptionValues("dataDir").get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return "success";
    }

    @GetMapping("/contact/{id}")
    public String getHandler(
        @PathVariable(name="id", required=true) String id, 
        Model model) throws IOException {

            User user = appService.getUserFromID(id, new File(cliOpts.getOptionValues("dataDir").get(0)));
            if (user != null) {
                model.addAttribute("user", user);
                return "result";
            } else {
                return "notfound";
            }
    }

    @GetMapping("/contacts")
    public String displayAllContactsWithLinks(Model model) {
        String[] contactIDs = appService.getAllContactIDs(new File(cliOpts.getOptionValues("dataDir").get(0)));
        model.addAttribute("contactIDs", contactIDs);
        return "contacts";
    }
}
