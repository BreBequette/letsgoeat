package com.liftoff.letsgoeat.models.data;

import com.liftoff.letsgoeat.models.Favorite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
}
