package ua.student.courseworktry3.model;

import ua.student.courseworktry3.dto.ProfitDTO;

import javax.persistence.*;

@Entity
@Table(name = "profit")
public class Profit {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private  Long id;

    @Column(name ="article",nullable = false)
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

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "account_id")
    private Account accountProfit;

    public Profit() {}

    public Profit(String article, Double january, Double february, Double march, Double april, Double may,
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

    public static Profit of(String article, Double january, Double february, Double march, Double april, Double may,
                            Double june, Double july, Double august, Double september, Double october, Double november,
                            Double december, Double sum) {
        return new Profit(article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
    }

    public ProfitDTO toDTO() {
        return ProfitDTO.of(id,article, january, february, march, april, may, june, july, august,
                september, october, november, december, sum);
    }

    public static Profit fromDTO(ProfitDTO profitDTO)  {
        return Profit.of(profitDTO.getArticle(),profitDTO.getJanuary(),profitDTO.getFebruary(),profitDTO.getMarch(),profitDTO.getApril(),
                profitDTO.getMay(), profitDTO.getJune(),profitDTO.getJuly(), profitDTO.getAugust(), profitDTO.getSeptember(), profitDTO.getOctober(),
                profitDTO.getNovember(), profitDTO.getDecember(), profitDTO.getSum());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Double getJanuary() {
        return january;
    }

    public void setJanuary(Double january) {
        this.january = january;
    }

    public Double getFebruary() {
        return february;
    }

    public void setFebruary(Double february) {
        this.february = february;
    }

    public Double getMarch() {
        return march;
    }

    public void setMarch(Double march) {
        this.march = march;
    }

    public Double getApril() {
        return april;
    }

    public void setApril(Double april) {
        this.april = april;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJune() {
        return june;
    }

    public void setJune(Double june) {
        this.june = june;
    }

    public Double getJuly() {
        return july;
    }

    public void setJuly(Double july) {
        this.july = july;
    }

    public Double getAugust() {
        return august;
    }

    public void setAugust(Double august) {
        this.august = august;
    }

    public Double getSeptember() {
        return september;
    }

    public void setSeptember(Double september) {
        this.september = september;
    }

    public Double getOctober() {
        return october;
    }

    public void setOctober(Double october) {
        this.october = october;
    }

    public Double getNovember() {
        return november;
    }

    public void setNovember(Double november) {
        this.november = november;
    }

    public Double getDecember() {
        return december;
    }

    public void setDecember(Double december) {
        this.december = december;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Account getAccountProfit() {
        return accountProfit;
    }

    public void setAccountProfit(Account accountProfit) {
        this.accountProfit = accountProfit;
    }

}
