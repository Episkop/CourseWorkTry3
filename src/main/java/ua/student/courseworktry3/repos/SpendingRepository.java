package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.courseworktry3.model.Spending;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
    boolean existsByArticle (String article);
    Spending findByArticle (String article);
}