package com.ads.robot.repositories;

import com.ads.robot.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LibraryRepository extends JpaRepository<Library,Integer> {
    public Library findByName(String name);

    @Transactional
    @Modifying
    @Query("delete from Library lib where lib.functions is empty ")
    public void deleteLibraryWithNoFunctions();

}
