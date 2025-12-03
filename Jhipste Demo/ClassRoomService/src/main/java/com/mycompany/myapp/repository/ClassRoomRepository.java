package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ClassRoom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Spring Data JPA repository for the ClassRoom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    List<ClassRoom> findByClassName(String className);
    boolean existsByClassName(String className);
    boolean existsByClassNameAndIdNot(String className, Long id);
}
