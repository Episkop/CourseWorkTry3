package ua.student.courseworktry3.model;

import lombok.Getter;
import ua.student.courseworktry3.dto.SpendingDTO;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "spending")
public class Spending {
    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false)
    private Long id;
    private String article;
    private Double january;
    private Double february;
    private Double march;
    private Double april;
    private Double may;
    private Double june;
    private Double july;
    private Double august;
    private Double september;
    private Double october;
    private Double november;
    private Double december;
    private Double year;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountSpending;


    public Spending(String article, Double january, Double february, Double march, Double april, Double may,
                    Double june, Double july, Double august, Double september, Double october, Double november,
                    Double december, Double year) {
        this.article = article;
        this.january = january;
        this.february = february;
        this.march = march;
        this.april = april;
        this.may = may;
        this.june = june;
        this.july = july;
        this.august = august;
        this.september = september;
        this.october = october;
        this.november = november;
        this.december = december;
        this.year = year;
    }
    public static Spending of(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year){
        return new Spending(article, january, february, march, april, may, june, july, august,
                september, october, november, december,year);
    }

    public SpendingDTO toDTO() {
        return SpendingDTO.of(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
    }

    public static Spending fromDTO (SpendingDTO spendingDTO){
        return Spending.of(spendingDTO.getArticle(),spendingDTO.getJanuary(),spendingDTO.getFebruary(),spendingDTO.getMarch(),spendingDTO.getApril(),
                spendingDTO.getMay(), spendingDTO.getJune(),spendingDTO.getJuly(), spendingDTO.getAugust(), spendingDTO.getSeptember(), spendingDTO.getOctober(),
                spendingDTO.getNovember(), spendingDTO.getDecember(), spendingDTO.getYear());
    }
    public Spending() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public void setYear(Double year) {
        this.year = year;
    }

    public void setAccountSpending(Account accountSpending) {
        this.accountSpending = accountSpending;
    }

    public void getAccountSpending(Account accountSpending) {
        this.accountSpending = accountSpending;
    }



}
