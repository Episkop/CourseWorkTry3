package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.courseworktry3.model.ProfitTotal;
import ua.student.courseworktry3.model.SpendingTotal;

import java.util.List;


public interface SpendingTotalRepository extends JpaRepository<SpendingTotal, Long> {

    boolean existsByArticle (String article);

    SpendingTotal findByArticle (String article);

    List<SpendingTotal> findByAccountSpendingTotalEmail (String email);
}