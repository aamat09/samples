package com.amatsolutions.samples.taskmanager.repository.carmarketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUserId(Long userId);
}
