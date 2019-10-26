package com.example.surveymanager.repository;

import com.example.surveymanager.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepo extends JpaRepository<Poll, Long>, JpaSpecificationExecutor<Poll> {
}
