package com.liftoff.letsgoeat.models.data;

import com.liftoff.letsgoeat.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);

}
