package ua.student.courseworktry3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.SpendingDTO;
import ua.student.courseworktry3.dto.SpendingTotalDTO;
import ua.student.courseworktry3.model.Account;
import ua.student.courseworktry3.model.Spending;
import ua.student.courseworktry3.model.SpendingTotal;
import ua.student.courseworktry3.repos.AccountRepository;
import ua.student.courseworktry3.repos.SpendingRepository;
import ua.student.courseworktry3.repos.SpendingTotalRepository;
import ua.student.courseworktry3.services.Interface.SpendingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingServiceImpl implements SpendingService {
    private final SpendingRepository spendingRepository;
    private final AccountRepository accountRepository;
    private final SpendingTotalRepository spendingTotalRepository;
    private final ProfitServiceImpl profitService;
    public SpendingServiceImpl(SpendingRepository spendingRepository, AccountRepository accountRepository,
                               SpendingTotalRepository spendingTotalRepository, ProfitServiceImpl profitService) {
        this.spendingRepository = spendingRepository;
        this.accountRepository = accountRepository;
        this.spendingTotalRepository = spendingTotalRepository;
        this.profitService = profitService;
    }
    @Transactional(readOnly = true)
    @Override
    public List<SpendingDTO> getAllSpending(String email) throws DBIsEmptyException {
        List<SpendingDTO> modelList = new ArrayList<>();
        List<Spending> list = spendingRepository.findByAccountSpendingEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }
    @Transactional
    @Override
    public List<SpendingTotalDTO> getAllTotalSpending(String email) throws DBIsEmptyException {
        List<SpendingTotalDTO> modelList = new ArrayList<>();
        List<SpendingTotal> list = spendingTotalRepository.findByAccountSpendingTotalEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Total is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }
    @Transactional
    @Override
    public boolean addSpending(String article, Double january, Double february, Double march, Double april, Double may,
                               Double june, Double july, Double august, Double september, Double october, Double november,
                               Double december, Double year, String email) throws AlreadyExistException {
        Account account = accountRepository.findByEmail(email);
        if (spendingRepository.existsByArticle(article))
            return  false;
        SpendingDTO dto = new SpendingDTO(article, january, february, march, april, may, june, july, august,
                september, october, november, december,year);
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
        account.addSpending(Spending.fromDTO(dto));
        countSum(email);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                profitService.countSum(email);
            }
            profitService.balance(email);
        }
        spendingRepository.sumSpendingLine(january, february, march, april, may, june, july, august,
                september, october, november, december, article);
        countSum(email);
        profitService.countSum(email);
        profitService.countSumLine(email);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public SpendingDTO findByArticle(String article, String email) throws NotFoundException, DBIsEmptyException {
        List<Spending> list = spendingRepository.findByAccountSpendingEmail(email);
        if (list.isEmpty()) {
            throw new DBIsEmptyException("Data Base is empty!");
        }
        Spending spending = list.stream().filter(x -> x.getArticle().equalsIgnoreCase(article)).findAny().orElse(null);
        return spending.toDTO();
    }

    @Transactional
    @Override
    public boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double year, String email) throws NotFoundException {
        Spending spending = spendingRepository.findByAccountSpendingEmailAndArticle(email,article);
        if (spending == null) {
            throw new NotFoundException("Such " + article + " don`t found");
        }
        if (january != null)
            spending.setJanuary(january);
        if (february != null)
            spending.setFebruary(february);
        if (march != null)
            spending.setMarch(march);
        if (april != null)
            spending.setApril(april);
        if (may != null)
            spending.setMay(may);
        if (june != null)
            spending.setJune(june);
        if (july != null)
            spending.setJuly(july);
        if (august != null)
            spending.setAugust(august);
        if (september != null)
            spending.setSeptember(september);
        if (october != null)
            spending.setOctober(october);
        if (november != null)
            spending.setNovember(november);
        if (december != null)
            spending.setDecember(december);

        countSum(email);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                profitService.countSum(email);
            }
            profitService.balance(email);
        }
        spendingRepository.sumSpendingLine(january, february, march, april, may, june, july, august,
                september, october, november, december, article);
        countSum(email);
        profitService.countSum(email);
        profitService.countSumLine(email);
        spendingRepository.save(spending);
        return true;
    }

    @Transactional
    @Override
    public void countSum(String email) {
        SpendingTotal ste = spendingTotalRepository.findByAccountSpendingTotalEmailAndArticle(email,"Total expenses");
        Double jan = spendingRepository.totalJan();
        ste.setJanuary(jan);
        Double feb = spendingRepository.totalFeb();
        ste.setFebruary(feb);
        Double mar = spendingRepository.totalMar();
        ste.setMarch(mar);
        Double apr = spendingRepository.totalApr();
        ste.setApril(apr);
        Double ma = spendingRepository.totalMay();
        ste.setMay(ma);
        Double jun = spendingRepository.totalJun();
        ste.setJune(jun);
        Double jul = spendingRepository.totalJul();
        ste.setJuly(jul);
        Double aug = spendingRepository.totalAug();
        ste.setAugust(aug);
        Double sep = spendingRepository.totalSep();
        ste.setSeptember(sep);
        Double oct = spendingRepository.totalOct();
        ste.setOctober(oct);
        Double nov = spendingRepository.totalNov();
        ste.setNovember(nov);
        Double dec = spendingRepository.totalDec();
        ste.setDecember(dec);
        Double su = spendingRepository.totalYear();
        ste.setYear(su);
        spendingTotalRepository.save(ste);
    }

    @Transactional
    @Override
    public void deleteSpending(String email, String article) throws NotFoundException {
       spendingRepository.findByAccountSpendingEmailAndArticle(email,article);
        spendingRepository.deleteSpendingByArticle(article);

        countSum(email);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 1; j++) {
                profitService.countSum(email);
            }
            profitService.balance(email);
        }
       profitService.countSumLine(email);
    }
}

