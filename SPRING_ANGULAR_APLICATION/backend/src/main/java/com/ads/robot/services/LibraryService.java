package com.ads.robot.services;

import com.ads.robot.entities.Library;
import com.ads.robot.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public List<Library> getLibraryList(){
        return libraryRepository.findAll();
    }
    public Library getLibrary(String name){
        return libraryRepository.findByName(name);
    }
    public Library saveLibrary(Library library){
        return libraryRepository.save(library);
    }
    public void deleteLibraryWithNoFunctions(){
        libraryRepository.deleteLibraryWithNoFunctions();
    }
}
