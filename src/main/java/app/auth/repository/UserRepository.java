package app.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.auth.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndEmail(String username, String email);
}
