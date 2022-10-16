package com.example.Trello.Controller;


import com.example.Trello.Entity.Folders;
import com.example.Trello.Entity.TaskCategories;
import com.example.Trello.Servies.FoldersServies;
import com.example.Trello.Servies.Security.UserServies;
import com.example.Trello.Servies.TaskCategoriesServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class test {

    @Autowired
    private TaskCategoriesServies taskCategoriesServies;
    @Autowired
    FoldersServies foldersServies;
    @Autowired
    private UserServies userServies;

//    @GetMapping(value = "/addCategories")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
//    private String addNewCat(Model model) {
//        List<TaskCategories> taskCategoriesList = taskCategoriesServies.allTaskCat();
//        model.addAttribute("categories", taskCategoriesList);
//
//        List<Folders> foldersList = foldersServies.allFolders();
//        model.addAttribute("folderlist", foldersList);
//
//        model.addAttribute("currentUser",userServies.getUserData());
//        return "Categories";
//    }
}
