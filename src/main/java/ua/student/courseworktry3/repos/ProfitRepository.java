package ua.student.courseworktry3.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.student.courseworktry3.model.Profit;


import java.util.List;


public interface ProfitRepository extends JpaRepository<Profit, Long> {
    List<Profit> findByAccountEmail(String email);
    Long countByAccountEmail(String email);
    Profit findByArticle (String article);
    boolean existsByArticle (String article);

//    @Query("SELECT NEW ua.student.courseworktry3.dto.TaskToNotifyDTO(a.email, t.date, t.text)" +
//            "FROM Account a, Profit t WHERE t.date >= :from AND t.date < :to")
//    List<TaskToNotifyDTO> findTasksToNotify(@Param("from") Date from,
//                                            @Param("to") Date to);
}
