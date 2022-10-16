package com.example.Trello.Repository;

import com.example.Trello.Entity.Folders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FoldersRepository extends JpaRepository<Folders,Long> {

}
