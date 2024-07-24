package com.amatsolutions.samples.taskmanager.controller.carmaketplace;

import com.amatsolutions.samples.taskmanager.model.carmarketplace.Favorite;
import com.amatsolutions.samples.taskmanager.service.carmarketplace.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }

    @PostMapping
    public Favorite addFavorite(@RequestBody Favorite favorite) {
        return favoriteService.addFavorite(favorite);
    }

    @DeleteMapping("/{id}")
    public void deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
    }
}
