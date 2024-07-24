package com.amatsolutions.samples.taskmanager.service.carmarketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Favorite;
import com.amatsolutions.samples.taskmanager.repository.carmarketplace.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}
