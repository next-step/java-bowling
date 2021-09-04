package qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import qna.domain.users.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccount(String account);
}
