package com.liftoff.letsgoeat.models.data;

import com.liftoff.letsgoeat.models.YelpSearch;
import com.liftoff.letsgoeat.service.YelpService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class SearchRepository implements JpaRepository<YelpService, Integer> {
}
