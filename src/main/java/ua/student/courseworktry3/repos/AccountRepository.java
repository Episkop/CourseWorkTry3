package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.courseworktry3.model.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
    Account findByEmail(String email);
}
