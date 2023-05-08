package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.courseworktry3.model.Profit;
import ua.student.courseworktry3.model.ProfitTotal;

import java.util.List;


public interface ProfitTotalRepository extends JpaRepository<ProfitTotal, Long> {

    boolean existsByArticle (String article);
    ProfitTotal findByAccountProfitTotalEmailAndArticle (String email, String article);

    ProfitTotal findByArticle (String article);

    List<ProfitTotal> findByAccountProfitTotalEmail (String email);
}