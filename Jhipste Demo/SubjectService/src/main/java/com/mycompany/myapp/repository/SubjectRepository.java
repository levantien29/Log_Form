package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Subject;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Subject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsBySubjectName(String subjectName);

    boolean existsBySubjectNameAndIdNot(String subjectName, Long id);

    boolean existsBySubjectNameAndTeacherIdIsNotNull(String subjectName);

    boolean existsByTeacherIdAndSubjectNameAndIdNot(Long teacherId, String subjectName, Long id);

    List<Subject> findByTeacherId(Long teacherId);
}
