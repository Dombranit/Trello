package com.example.Trello.Controller;

import com.example.Trello.Entity.Folders;
import com.example.Trello.Entity.Security.Users;
import com.example.Trello.Entity.TaskCategories;
import com.example.Trello.Entity.Tasks;
import com.example.Trello.Servies.FoldersServies;
import com.example.Trello.Servies.Security.UserServies;
import com.example.Trello.Servies.TaskCategoriesServies;
import com.example.Trello.Servies.TaskServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FoldersController {
    @Autowired
    private FoldersServies foldersServies;
    @Autowired
    private TaskServies taskServies;

    @Autowired
    private TaskCategoriesServies taskCategoriesServies;

    @Autowired
    private UserServies userServies;


    @GetMapping(value= "/Trello")
    @PreAuthorize("isAuthenticated()")
    public String openTrello(Model model) {
        List<Folders> foldersList = foldersServies.allFolders();
        model.addAttribute("foldersList", foldersList);

        List<Tasks> tasksList = taskServies.allTasks();
        model.addAttribute("tasks",tasksList);

        model.addAttribute("currentUser",userServies.getUserData());

        return "Trello";
    }

    @PostMapping(value = "/addFolder")
    public String addFolder(@RequestParam(name = "folder_name") String name,
                            @RequestParam(name = "user_id")Long userId) {
        Folders folders = new Folders();
        Users currentUser=userServies.getUser(userId);
        folders.setName(name);
        folders.setUsers(currentUser);
        foldersServies.addFolder(folders);
        return "redirect:/Trello";
    }

    @GetMapping(value = "/Folder/{ID}")
    @PreAuthorize("isAuthenticated()")
    public String openFolder(Model model, @PathVariable(name = "ID") Long id) {
        List<Tasks> tasksList = taskServies.allTasks();
        model.addAttribute("tasks", tasksList);

        List<Folders> foldersList = foldersServies.allFolders();
        model.addAttribute("foldersList", foldersList);

        List<TaskCategories> taskCategoriesList = taskCategoriesServies.allTaskCat();
        model.addAttribute("categories", taskCategoriesList);

        Folders folders = foldersServies.getFolder(id);
        model.addAttribute("folders", folders);

        model.addAttribute("currentUser",userServies.getUserData());

        return "detailsFolder";
    }

    @PostMapping(value = "/deleteFolder")
    public String deleteFolder(@RequestParam(name = "folderId") Long folderID,
                               @RequestParam(name = "tasksId") Long tasksID,
                               @RequestParam(name = "catsId") Long catsID) {
        Folders folders = foldersServies.getFolder(folderID);
        TaskCategories categories = taskCategoriesServies.getTaskCat(catsID);
        List<Tasks> tasksList = taskServies.allTasks();
        for (Tasks delTask : tasksList) {
            if (delTask != null) {
                if (folderID.equals(delTask.getFolder().getId())) {
                    taskServies.deleteTask(delTask);
                }
            }
        }
        List<TaskCategories> categoriesList = folders.getCategoriesList();
        if (categoriesList != null) {
            for (int i = 0; i < categoriesList.size(); i++) {
                categoriesList.remove(categories);
                folders.setCategoriesList(categoriesList);
                foldersServies.saveFolder(folders);
            }
            foldersServies.deleteFolder(folders);
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deleteEmtyFolder")
    public String delEmtFol(@RequestParam(name = "folderId")Long id){
        Folders folders=foldersServies.getFolder(id);
        if(folders!=null){
            foldersServies.deleteFolder(folders);
         }
        return "redirect:/";
    }
    @GetMapping(value = "/Eror500")
    public String eror500(){
    return "Eror500";
    }
}
