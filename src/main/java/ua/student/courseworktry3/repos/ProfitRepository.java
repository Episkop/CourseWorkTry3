package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.student.courseworktry3.model.Profit;


import java.util.List;


public interface ProfitRepository extends JpaRepository<Profit, Long> {
    List<Profit> findByAccountProfitEmail(String email);
    Long countByAccountProfitEmail(String email);
    Profit findByArticle (String article);
    boolean existsByArticle (String article);



    @Modifying
    @Query("UPDATE Profit e SET e.year = e.january + e.february + e.march + e.april + e.may + " +
            "e.june + e.july + e.august + e.september + e.october + e.november + e.december WHERE e.article = e.article")
    void sumProfitLine(Double january, Double february, Double march, Double april, Double may,
                       Double june, Double july, Double august, Double september, Double october, Double november,
                       Double december, String article);

    @Query("SELECT sum (e.january) from Profit e ")
    Double totalJan();
    @Query("SELECT sum (e.february) from Profit e ")
    Double totalFeb();
    @Query("SELECT sum (e.march) from Profit e ")
    Double totalMar();
    @Query("SELECT sum (e.april) from Profit e ")
    Double totalApr();
    @Query("SELECT sum (e.may) from Profit e ")
    Double totalMay();
    @Query("SELECT sum (e.june) from Profit  e ")
    Double totalJun();
    @Query("SELECT sum (e.july) from Profit e ")
    Double totalJul();
    @Query("SELECT sum (e.august) from Profit e ")
    Double totalAug();
    @Query("SELECT sum (e.september) from Profit e ")
    Double totalSep();
    @Query("SELECT sum (e.october) from Profit e ")
    Double totalOct();
    @Query("SELECT sum (e.november) from Profit e ")
    Double totalNov();
    @Query("SELECT sum (e.december) from Profit e ")
    Double totalDec();
    @Query("SELECT sum (e.year) from Profit e ")
    Double totalSum();
}
