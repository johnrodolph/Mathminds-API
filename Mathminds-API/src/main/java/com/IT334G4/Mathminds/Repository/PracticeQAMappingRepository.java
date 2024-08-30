package com.IT334G4.Mathminds.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.IT334G4.Mathminds.Entity.PracticeQaMappingEntity;
import com.IT334G4.Mathminds.OtherClasses.PracticeQA;

@Repository
public interface PracticeQAMappingRepository extends JpaRepository<PracticeQaMappingEntity, Integer> {
    @Query("SELECT pqm.practiceQA FROM PracticeQaMappingEntity pqm WHERE pqm.practice.practiceId = :practiceId")
    List<PracticeQA> findPracticeQAsByPracticeId(@Param("practiceId") int practiceId);

}
