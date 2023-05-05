package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.student.courseworktry3.model.Spending;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
    boolean existsByArticle (String article);
    Spending findByArticle (String article);
    List<Spending> findByAccountSpendingEmail(String email);

    @Modifying
    @Query("UPDATE Spending e SET e.sum = e.january + e.february + e.march + e.april + e.may + " +
            "e.june + e.july + e.august + e.september + e.october + e.november + e.december WHERE e.article = e.article")
    void sumProfitLine(Double january, Double february, Double march, Double april, Double may,
                       Double june, Double july, Double august, Double september, Double october, Double november,
                       Double december, String article);

    @Query("SELECT sum (e.january) from Spending e ")
    Double totalJan();
    @Query("SELECT sum (e.february) from Spending e ")
    Double totalFeb();
    @Query("SELECT sum (e.march) from Spending e ")
    Double totalMar();
    @Query("SELECT sum (e.april) from Spending e ")
    Double totalApr();
    @Query("SELECT sum (e.may) from Spending e ")
    Double totalMay();
    @Query("SELECT sum (e.june) from Spending  e ")
    Double totalJun();
    @Query("SELECT sum (e.july) from Spending e ")
    Double totalJul();
    @Query("SELECT sum (e.august) from Spending e ")
    Double totalAug();
    @Query("SELECT sum (e.september) from Spending e ")
    Double totalSep();
    @Query("SELECT sum (e.october) from Spending e ")
    Double totalOct();
    @Query("SELECT sum (e.november) from Spending e ")
    Double totalNov();
    @Query("SELECT sum (e.december) from Spending e ")
    Double totalDec();
    @Query("SELECT sum (e.sum) from Spending e ")
    Double totalSum();
}
