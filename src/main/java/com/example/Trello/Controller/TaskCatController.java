package com.example.Trello.Controller;

import com.example.Trello.Entity.Folders;
import com.example.Trello.Entity.Security.Users;
import com.example.Trello.Entity.TaskCategories;
import com.example.Trello.Servies.FoldersServies;
import com.example.Trello.Servies.Security.UserServies;
import com.example.Trello.Servies.TaskCategoriesServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskCatController {
    @Autowired
    private TaskCategoriesServies taskCategoriesServies;
    @Autowired
    private FoldersServies foldersServies;

    @Autowired
    private UserServies userServies;

    @PostMapping(value = "/unassign-Categories")
    private String unassign(@RequestParam(name = "cat_id") Long catid,
                            @RequestParam(name = "folder_id") Long folderid) {
        TaskCategories categories = taskCategoriesServies.getTaskCat(catid);
        if (categories != null) {
            Folders folders = foldersServies.getFolder(folderid);
            if (folders != null) {
                List<TaskCategories> categoriesList = folders.getCategoriesList();
                if (categoriesList != null) {
                    categoriesList.remove(categories);
                    folders.setCategoriesList(categoriesList);
                    foldersServies.saveFolder(folders);
                }
            }
        }
        return "redirect:/Folder/" + folderid;
    }

    @PostMapping(value = "assign-Categories")
    private String assign(@RequestParam(name = "cat_id") Long catid,
                          @RequestParam(name = "folder_id") Long folderid) {
        TaskCategories categories = taskCategoriesServies.getTaskCat(catid);
        if (categories != null) {
            Folders folders = foldersServies.getFolder(folderid);
            if (folders != null) {
                List<TaskCategories> categoriesList = folders.getCategoriesList();
                if(categoriesList!=null){
                    categoriesList.add(categories);
                    folders.setCategoriesList(categoriesList);
                    foldersServies.saveFolder(folders);
                }
            }
        }
        return "redirect:/Folder/" + folderid;
    }

    @GetMapping(value = "/addCategories")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    private String addNewCat(Model model) {
        List<TaskCategories> taskCategoriesList = taskCategoriesServies.allTaskCat();
        model.addAttribute("categories", taskCategoriesList);

        List<Folders> foldersList = foldersServies.allFolders();
        model.addAttribute("folderlist", foldersList);

        model.addAttribute("currentUser",userServies.getUserData());
        return "Categories";
    }

    @PostMapping(value = "/addCategories")
    private String NewCat(@RequestParam(name = "cat_name") String name) {
        TaskCategories categories = new TaskCategories(null, name);
        taskCategoriesServies.addTaskCat(categories);
        return "redirect:/addCategories";
    }

//    @PostMapping(value = "/deleteCat")
//    public String deleteCat(@RequestParam(name = "cat_id") Long catId,
//                            @RequestParam(name = "folder_id") Long folderId) {
//        TaskCategories categories = taskCategoriesServies.getTaskCat(catId);
//        List<Folders> foldersList = foldersServies.allFolders();
//        Folders folders = foldersServies.getFolder(folderId);
//        for (int i = 0; i < foldersList.size(); i++) {
//            List<TaskCategories> categoriesList = folders.getCategoriesList();
//            if (categoriesList != null) {
//                for (int j = 0; j < categoriesList.size(); j++) {
//                    categoriesList.remove(categories);
//                    folders.setCategoriesList(categoriesList);
//                    foldersServies.saveFolder(folders);
//                }
//                taskCategoriesServies.deleteTaskCat(categories);
//            }
//        }
//        return "redirect:/addCategories";
//    }
}
