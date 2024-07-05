package com.amatsolutions.samples.taskmanager.repository;

import com.amatsolutions.samples.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDateBetween(Date startDate, Date endDate);
}
