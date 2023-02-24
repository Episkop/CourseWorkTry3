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
    private Double sum;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountSP;


    public Spending(String article, Double january, Double february, Double march, Double april, Double may,
                    Double june, Double july, Double august, Double september, Double october, Double november,
                    Double december, Double sum) {
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
        this.sum = sum;
    }
    public static Spending of(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double sum){
        return new Spending(article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
    }

    public SpendingDTO toDTO() {
        return SpendingDTO.of(id,article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
    }

    public static Spending fromDTO (SpendingDTO spendingDTO){
        return Spending.of(spendingDTO.getArticle(),spendingDTO.getJanuary(),spendingDTO.getFebruary(),spendingDTO.getMarch(),spendingDTO.getApril(),
                spendingDTO.getMay(), spendingDTO.getJune(),spendingDTO.getJuly(), spendingDTO.getAugust(), spendingDTO.getSeptember(), spendingDTO.getOctober(),
                spendingDTO.getNovember(), spendingDTO.getDecember(), spendingDTO.getSum());
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

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public void setAccountSP(Account accountSP) {
        this.accountSP = accountSP;
    }

    public void getAccountSP(Account account) {
        this.accountSP = accountSP;
    }



}
