package org.example.repository;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //репозиторий позволяет манипулировать таблицей
    Optional<User> findByEmail(String email);


    Optional<User> findById(Long id);
}
