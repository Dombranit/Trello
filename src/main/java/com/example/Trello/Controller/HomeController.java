package com.example.Trello.Controller;

import com.example.Trello.Entity.Folders;
import com.example.Trello.Entity.Tasks;
import com.example.Trello.Servies.FoldersServies;
import com.example.Trello.Servies.Security.UserServies;
import com.example.Trello.Servies.TaskCategoriesServies;
import com.example.Trello.Servies.TaskServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Controller
public class HomeController {

    @Autowired
    private UserServies userServies;

    @GetMapping(value = "/main")
    public String main(Model model){
        model.addAttribute("currentUser",userServies.getUserData());
        return "layout/main";
    }
}

