package ua.student.courseworktry3.model;

import ua.student.courseworktry3.dto.AccountDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String email;
    private String name;
    private String pictureUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Profit> profits = new ArrayList<>();

    @OneToMany(mappedBy = "accountSP", cascade = CascadeType.ALL)
    private List<Spending> spendings = new ArrayList<>();

    @OneToOne(mappedBy = "accountPT",cascade = CascadeType.ALL)
    private ProfitTotal totalProf = new ProfitTotal();

    @OneToOne(mappedBy = "accountST",cascade = CascadeType.ALL)
    private SpendingTotal totalSpend = new SpendingTotal();
    public Account() {}

    private Account(String email, String name, String pictureUrl) {
        this.email = email;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }

    public static Account of(String email, String name, String pictureUrl) {
        return new Account(email, name, pictureUrl);
    }

    public void addProfit(Profit profit) {
        profit.setAccount(this);
        profits.add(profit);
    }

    public void addSpending(Spending spending) {
        spending.setAccountSP(this);
        spendings.add(spending);
    }

    public void addProfitTotal(ProfitTotal profitTotal){
        profitTotal.setAccount(this);
        totalProf = profitTotal;
    }

    public void addSpendingTotal(SpendingTotal spendingTotal){
        spendingTotal.setAccount(this);
        totalSpend = spendingTotal;
    }
    public AccountDTO toDTO() {
        return AccountDTO.of(email, name, pictureUrl);
    }

    public static Account fromDTO(AccountDTO accountDTO) {
        return Account.of(accountDTO.getEmail(), accountDTO.getName(), accountDTO.getPictureUrl());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Profit> getProfits() {
        return profits;
    }

    public void setProfits(List<Profit> profits) {
        this.profits = profits;
    }

    public List<Spending> getSpendings() {
        return spendings;
    }

    public void setSpendings(List<Spending> spendings) {
        this.spendings = spendings;
    }

    public ProfitTotal getTotalProf() {
        return totalProf;
    }

    public void setTotalProf(ProfitTotal totalProf) {
        this.totalProf = totalProf;
    }
}
