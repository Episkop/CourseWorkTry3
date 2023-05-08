package ua.student.courseworktry3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.ProfitDTO;
import ua.student.courseworktry3.dto.ProfitTotalDTO;
import ua.student.courseworktry3.model.Account;
import ua.student.courseworktry3.model.Profit;
import ua.student.courseworktry3.model.ProfitTotal;
import ua.student.courseworktry3.repos.AccountRepository;
import ua.student.courseworktry3.repos.ProfitRepository;
import ua.student.courseworktry3.repos.ProfitTotalRepository;
import ua.student.courseworktry3.services.Interface.ProfitService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitServiceImpl implements ProfitService {

    private final ProfitRepository profitRepository;
    private final AccountRepository accountRepository;
    private final ProfitTotalRepository profitTotalRepository;

    public ProfitServiceImpl(ProfitRepository profitRepository, AccountRepository accountRepository,
                             ProfitTotalRepository profitTotalRepository) {
        this.profitRepository = profitRepository;
        this.accountRepository = accountRepository;
        this.profitTotalRepository = profitTotalRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfitDTO> getAllProfit(String email) throws DBIsEmptyException {
        List<ProfitDTO> modelList = new ArrayList<>();
        List<Profit> list = profitRepository.findByAccountProfitEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfitTotalDTO> getAllTotalProfit(String email) throws DBIsEmptyException {
        List<ProfitTotalDTO> modelList = new ArrayList<>();
        List<ProfitTotal> list = profitTotalRepository.findByAccountProfitTotalEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Total is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }

    @Transactional
    @Override
    public boolean addProfit(String article, Double january, Double february, Double march, Double april, Double may,
                             Double june, Double july, Double august, Double september, Double october, Double november,
                             Double december, Double year, String email) throws AlreadyExistException {
        Account account = accountRepository.findByEmail(email);
        if (profitRepository.existsByArticle(article))
            return false;
        ProfitDTO dto = new ProfitDTO(article, january, february, march, april, may, june, july, august,
                september, october, november, december, year);
        if (january == null)
            dto.setJanuary(0.0);
        if (february == null)
            dto.setFebruary(0.0);
        if (march == null)
            dto.setMarch(0.0);
        if (april == null)
            dto.setApril(0.0);
        if (may == null)
            dto.setMay(0.0);
        if (june == null)
            dto.setJune(0.0);
        if (july == null)
            dto.setJuly(0.0);
        if (august == null)
            dto.setAugust(0.0);
        if (september == null)
            dto.setSeptember(0.0);
        if (october == null)
            dto.setOctober(0.0);
        if (november == null)
            dto.setNovember(0.0);
        if (december == null)
            dto.setDecember(0.0);
        if (year == null)
            dto.setYear(year);
        account.addProfit(Profit.fromDTO(dto));
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum(email);
            }
            balance(email);
        }
        countSumLine(email);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfitDTO findByArticle(String article, String email) throws NotFoundException, DBIsEmptyException {
        List<Profit> list = profitRepository.findByAccountProfitEmail(email);
        if (list.isEmpty()) {
            throw new DBIsEmptyException("Data Base is empty!");
        }
        Profit profit = list.stream().filter(x -> x.getArticle().equalsIgnoreCase(article)).findAny().orElse(null);
        return profit.toDTO();
    }

    @Transactional
    @Override
    public boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                                Double june, Double july, Double august, Double september, Double october, Double november,
                                Double december, Double year, String email) throws NotFoundException {
        Profit profit = profitRepository.findByAccountProfitEmailAndArticle(email, article);
        if (profit == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if ("Balance at the beginning".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if ("Opening balance".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            profit.setJanuary(january);
        if (february != null)
            profit.setFebruary(february);
        if (march != null)
            profit.setMarch(march);
        if (april != null)
            profit.setApril(april);
        if (may != null)
            profit.setMay(may);
        if (june != null)
            profit.setJune(june);
        if (july != null)
            profit.setJuly(july);
        if (august != null)
            profit.setAugust(august);
        if (september != null)
            profit.setSeptember(september);
        if (october != null)
            profit.setOctober(october);
        if (november != null)
            profit.setNovember(november);
        if (december != null)
            profit.setDecember(december);

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum(email);
            }
            balance(email);
        }
        countSumLine(email);
        profitRepository.save(profit);
        return true;
    }

    @Transactional
    @Override
    public void countSumLine(String email) {
        profitRepository.sumProfitLine();
        countSum(email);
    }

    @Transactional
    @Override
    public void countSum(String email) {

        ProfitTotal pte = profitTotalRepository.findByAccountProfitTotalEmailAndArticle(email, "Total incomes");
        Double jan = profitRepository.totalJan();
        pte.setJanuary(jan);
        Double feb = profitRepository.totalFeb();
        pte.setFebruary(feb);
        Double mar = profitRepository.totalMar();
        pte.setMarch(mar);
        Double apr = profitRepository.totalApr();
        pte.setApril(apr);
        Double ma = profitRepository.totalMay();
        pte.setMay(ma);
        Double jun = profitRepository.totalJun();
        pte.setJune(jun);
        Double jul = profitRepository.totalJul();
        pte.setJuly(jul);
        Double aug = profitRepository.totalAug();
        pte.setAugust(aug);
        Double sep = profitRepository.totalSep();
        pte.setSeptember(sep);
        Double oct = profitRepository.totalOct();
        pte.setOctober(oct);
        Double nov = profitRepository.totalNov();
        pte.setNovember(nov);
        Double dec = profitRepository.totalDec();
        pte.setDecember(dec);
        Double su = profitRepository.totalYear();
        pte.setYear(su);
        profitTotalRepository.save(pte);
    }

    @Transactional
    @Override
    public void balance(String email) {
        Profit profitRest = profitRepository.findByAccountProfitEmailAndArticle(email, "Balance at the beginning");
        Double february = profitRepository.restForFebruary();
        if (february == 0)
            profitRest.setFebruary(0.0);
        else
            profitRest.setFebruary(february);
        Double march = profitRepository.restForMarch();
        if (march == 0)
            profitRest.setMarch(0.0);
        else
            profitRest.setMarch(march);
        Double april = profitRepository.restForApril();
        if (april == 0)
            profitRest.setApril(0.0);
        else
            profitRest.setApril(april);
        Double may = profitRepository.restForMay();
        if (may == 0)
            profitRest.setMay(0.0);
        else
            profitRest.setMay(may);
        Double june = profitRepository.restForJune();
        if (june == 0)
            profitRest.setJune(0.0);
        else
            profitRest.setJune(june);
        Double july = profitRepository.restForJuly();
        if (july == 0)
            profitRest.setJuly(0.0);
        else
            profitRest.setJuly(july);
        Double august = profitRepository.restForAugust();
        if (august == 0)
            profitRest.setAugust(0.0);
        else
            profitRest.setAugust(august);
        Double september = profitRepository.restForSeptember();
        if (september == 0)
            profitRest.setSeptember(0.0);
        else
            profitRest.setSeptember(september);
        Double october = profitRepository.restForOctober();
        if (october == 0)
            profitRest.setOctober(0.0);
        else
            profitRest.setOctober(october);
        Double november = profitRepository.restForNovember();
        if (november == 0)
            profitRest.setNovember(0.0);
        else
            profitRest.setNovember(november);
        Double december = profitRepository.restForDecember();
        if (december == 0)
            profitRest.setDecember(0.0);
        else
            profitRest.setDecember(december);
        profitRepository.save(profitRest);
    }

    //    Сохранение в "Opening balance"
    @Transactional
    @Override
    public boolean startUpCapital(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year, String email) throws NotFoundException {
        Profit profit = profitRepository.findByAccountProfitEmailAndArticle(email, "Opening balance");
        if (profit == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            profit.setJanuary(january);
        if (february != null && profit.getJanuary() == 0)
            profit.setFebruary(february);
        if (march != null && profit.getJanuary() == 0 && profit.getFebruary() == 0)
            profit.setMarch(march);
        if (april != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0)
            profit.setApril(april);
        if (may != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0)
            profit.setMay(may);
        if (june != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0)
            profit.setJune(june);
        if (july != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0 && profit.getJune() == 0)
            profit.setJuly(july);
        if (august != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0 && profit.getJune() == 0 && profit.getJuly() == 0)
            profit.setAugust(august);
        if (september != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0 && profit.getJune() == 0 && profit.getJuly() == 0 && profit.getAugust() == 0)
            profit.setSeptember(september);
        if (october != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0 && profit.getJune() == 0 && profit.getJuly() == 0 && profit.getAugust() == 0 && profit.getSeptember() == 0)
            profit.setOctober(october);
        if (november != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0 && profit.getJune() == 0 && profit.getJuly() == 0 && profit.getAugust() == 0 && profit.getSeptember() == 0 && profit.getOctober() == 0)
            profit.setNovember(november);
        if (december != null && profit.getJanuary() == 0 && profit.getFebruary() == 0 && profit.getMarch() == 0 && profit.getApril() == 0 && profit.getMay() == 0 && profit.getJune() == 0 && profit.getJuly() == 0 && profit.getAugust() == 0 && profit.getSeptember() == 0 && profit.getOctober() == 0 && profit.getNovember() == 0)
            profit.setDecember(december);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum(email);
            }
            balance(email);
        }
        countSumLine(email);
        profitRepository.save(profit);
        return true;
    }

    @Transactional
    @Override
    public void deleteProfit(String email, String article) throws NotFoundException {
        Profit profit = profitRepository.findByAccountProfitEmailAndArticle(email, article);
        if ("Balance at the beginning".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " can not be deleted");
        }
        if ("Opening balance".equals(profit.getArticle())) {
            throw new NotFoundException("Such " + article + " can not be deleted");
        }
        profitRepository.deleteProfitEntityByArticle(article);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                countSum(email);
            }
            balance(email);
        }
        countSumLine(email);
    }
}
