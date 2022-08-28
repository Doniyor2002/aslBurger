package com.example.botservice.repository;

import com.example.botservice.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface SupportRepository extends JpaRepository<Support, Long> {
    @Query(value = "SELECT t FROM Support t WHERE t.phoneList=:phoneList and t.description=:description")
    Support findByPhoneListAndDescription(@Param("phoneList") Set<String> phoneList,@Param("description") String description);
}
