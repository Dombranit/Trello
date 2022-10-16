package com.example.Trello.Controller;

import com.example.Trello.Entity.Folders;
import com.example.Trello.Entity.Security.Users;
import com.example.Trello.Entity.Tasks;
import com.example.Trello.Servies.FoldersServies;
import com.example.Trello.Servies.TaskServies;
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
public class TaskController {

    @Autowired
    private FoldersServies foldersServies;

    @Autowired TaskServies taskServies;


    @PostMapping(value = "/add-Task")
    private String addTask(@RequestParam(name = "task_title")String title,
                           @RequestParam(name = "task_description")String desc,
                           @RequestParam(name = "folder_id")Long id){
        Folders folders = foldersServies.getFolder(id);
        if(folders!=null) {
            Tasks tasks = new Tasks();
            tasks.setTitle(title);
            tasks.setDescription(desc);
            tasks.setStatus(0);
            tasks.setFolder(folders);
            taskServies.saveTask(tasks);
        }
        return "redirect:/Folder/"+id;
    }

    @GetMapping(value = "/Task/{ID}")
    @PreAuthorize("isAuthenticated()")
    public String getTask(Model model, @PathVariable(name = "ID")Long id){
        Tasks tasks= taskServies.getTask(id);
        model.addAttribute("task",tasks);
        Folders folders = foldersServies.getFolder(id);
        model.addAttribute("folders",folders);
        return "detailsTask";
    }

    @PostMapping(value = "/save-Task")
    public String saveTask(@RequestParam(name = "folder_id")Long folID,
                           @RequestParam(name = "task_id")Long taskID,
                           @RequestParam(name = "task_name")String title,
                           @RequestParam(name = "task_description")String desc,
                           @RequestParam(name = "task_status")int status){
        Folders folders=foldersServies.getFolder(folID);
        if(folders!=null){
            Tasks tasks = taskServies.getTask(taskID);
                if(tasks!=null){
                    tasks.setFolder(folders);
                    tasks.setId(taskID);
                    tasks.setTitle(title);
                    tasks.setDescription(desc);
                    tasks.setStatus(status);
                    taskServies.saveTask(tasks);
                }
        }
            return "redirect:/Folder/"+folID;
    }
    @PostMapping(value = "/deleteTask")
    public String deleteTask(@RequestParam(name = "taskID")Long taskID,
                             @RequestParam(name = "folderId") Long folderId){
        Tasks tasks = taskServies.getTask(taskID);
        if(tasks!=null){
            taskServies.deleteTask(tasks);
        }
        return "redirect:/Folder/"+folderId;
    }
}
