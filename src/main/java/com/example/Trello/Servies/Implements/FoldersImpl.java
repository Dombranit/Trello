package com.example.Trello.Servies.Implements;

import com.example.Trello.Entity.Folders;
import com.example.Trello.Repository.FoldersRepository;
import com.example.Trello.Servies.FoldersServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoldersImpl implements FoldersServies {

    @Autowired
    private FoldersRepository repository;

    @Override
    public List<Folders> allFolders() {
        return repository.findAll();
    }

    @Override
    public Folders addFolder(Folders folders) {
        return repository.save(folders);
    }

    @Override
    public Folders saveFolder(Folders folders) {
        return repository.save(folders);
    }

    @Override
    public void deleteFolder(Folders folders) {
        repository.delete(folders);
    }

    @Override
    public Folders getFolder(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteFolderById(Long id) {
        repository.deleteById(id);
    }
}
