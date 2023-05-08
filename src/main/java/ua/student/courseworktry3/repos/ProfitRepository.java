package ua.student.courseworktry3.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import ua.student.courseworktry3.model.Profit;


import java.util.List;


public interface ProfitRepository extends JpaRepository<Profit, Long> {
    List<Profit> findByAccountProfitEmail(String email);
    Profit findByAccountProfitEmailAndArticle (String email, String article);
    Long countByAccountProfitEmail(String email);
    Profit findByArticle (String article);
    boolean existsByArticle (String article);

    @Query("SELECT e.january - p.january from ProfitTotal e, SpendingTotal p" )
    Double restForFebruary();
    @Query("SELECT e.february - p.february from ProfitTotal e, SpendingTotal p" )
    Double restForMarch();
    @Query("SELECT e.march - p.march from ProfitTotal e, SpendingTotal p" )
    Double restForApril();
    @Query("SELECT e.april - p.april from ProfitTotal e, SpendingTotal p" )
    Double restForMay();
    @Query("SELECT e.may - p.may from ProfitTotal e, SpendingTotal p" )
    Double restForJune();
    @Query("SELECT e.june - p.june from ProfitTotal e, SpendingTotal p" )
    Double restForJuly();
    @Query("SELECT e.july - p.july from ProfitTotal e, SpendingTotal p" )
    Double restForAugust();
    @Query("SELECT e.august - p.august from ProfitTotal e, SpendingTotal p" )
    Double restForSeptember();
    @Query("SELECT e.september - p.september from ProfitTotal e, SpendingTotal p" )
    Double restForOctober();
    @Query("SELECT e.october - p.october from ProfitTotal e, SpendingTotal p" )
    Double restForNovember();
    @Query("SELECT e.november - p.november from ProfitTotal e, SpendingTotal p" )
    Double restForDecember();

    void deleteProfitEntityByArticle (String article);

    @Modifying
    @Query("UPDATE Profit e SET e.year = e.january + e.february + e.march + e.april + e.may + " +
            "e.june + e.july + e.august + e.september + e.october + e.november + e.december WHERE e.article = e.article and not e.article = 'Balance at the beginning'")
    void sumProfitLine();


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
    Double totalYear();
}
