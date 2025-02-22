package ru.cource.task78.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.cource.task78.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    void deleteByUsername(String username);
    Optional<User> findByUsername(String username);
}
