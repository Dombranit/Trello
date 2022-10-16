package com.example.Trello.Servies;

import com.example.Trello.Entity.Folders;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FoldersServies {
    List<Folders> allFolders ();
    Folders addFolder(Folders folders);
    Folders saveFolder(Folders folders);
    void deleteFolder(Folders folders);
    Folders getFolder(Long id);

    void deleteFolderById(Long id);

}
