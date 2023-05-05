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

    public SpendingServiceImpl(SpendingRepository spendingRepository, AccountRepository accountRepository,
                               SpendingTotalRepository spendingTotalRepository) {
        this.spendingRepository = spendingRepository;
        this.accountRepository = accountRepository;
        this.spendingTotalRepository = spendingTotalRepository;
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
    public boolean addSpending(String article, Double january, Double february, Double march, Double april, Double may,
                               Double june, Double july, Double august, Double september, Double october, Double november,
                               Double december, Double sum, String email) throws AlreadyExistException {
        Account account = accountRepository.findByEmail(email);
        if (spendingRepository.existsByArticle(article))
            return  false;
        SpendingDTO model = new SpendingDTO(article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
        if (january == null)
            model.setJanuary(0.0);
        if (february == null)
            model.setFebruary(0.0);
        if (march == null)
            model.setMarch(0.0);
        if (april == null)
            model.setApril(0.0);
        if (may == null)
            model.setMay(0.0);
        if (june == null)
            model.setJune(0.0);
        if (july == null)
            model.setJuly(0.0);
        if (august == null)
            model.setAugust(0.0);
        if (september == null)
            model.setSeptember(0.0);
        if (october == null)
            model.setOctober(0.0);
        if (november == null)
            model.setNovember(0.0);
        if (december == null)
            model.setDecember(0.0);
        if (sum == null)
            model.setSum(sum);
        account.addSpending(Spending.fromDTO(model));
        return true;
    }
//

    @Transactional(readOnly = true)
    @Override
    public Spending findByArticle(String article) throws NotFoundException {
        Spending spending = spendingRepository.findByArticle(article);
        if (spending == null) {
            throw new NotFoundException("Didn`t find article " + spending.getArticle());
        }
        return spending;
    }

    @Transactional
    @Override
    public List<SpendingTotalDTO> getTotal(String email) throws DBIsEmptyException {
        List<SpendingTotalDTO> modelList = new ArrayList<>();
        List<SpendingTotal> list = spendingTotalRepository.findByAccountSpendingTotalEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Total is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }

    @Transactional
    @Override
    public boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                                  Double june, Double july, Double august, Double september, Double october, Double november,
                                  Double december, Double sum, String email) throws NotFoundException {
        Account account = accountRepository.findByEmail(email);
        if (spendingRepository.existsByArticle(article))
            return  false;
//        ProfitDTO profit = profitRepository.findByArticle(article);
//        if (profit == null) {
//            throw new NotFoundException("Such " + article + " don`t found");
//        }
        SpendingDTO spending = new SpendingDTO(article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum);
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
        if (sum != null)
            spending.setSum(sum);
        //TODO
        spendingRepository.sumProfitLine(january, february, march, april, may, june, july, august,
                september, october, november, december,article);
        account.addSpending(Spending.fromDTO(spending));
//        profitRepository.save(profit);

        SpendingTotal pte = spendingTotalRepository.findByArticle("total");
        Double jan = spendingRepository.totalJan();
        pte.setJanuary(jan);
        Double feb = spendingRepository.totalFeb();
        pte.setFebruary(feb);
        Double mar = spendingRepository.totalMar();
        pte.setMarch(mar);
        Double apr = spendingRepository.totalApr();
        pte.setApril(apr);
        Double ma = spendingRepository.totalMay();
        pte.setMay(ma);
        Double jun = spendingRepository.totalJun();
        pte.setJune(jun);
        Double jul = spendingRepository.totalJul();
        pte.setJuly(jul);
        Double aug = spendingRepository.totalAug();
        pte.setAugust(aug);
        Double sep = spendingRepository.totalSep();
        pte.setSeptember(sep);
        Double oct = spendingRepository.totalOct();
        pte.setOctober(oct);
        Double nov = spendingRepository.totalNov();
        pte.setNovember(nov);
        Double dec = spendingRepository.totalDec();
        pte.setDecember(dec);
        Double su = spendingRepository.totalSum();
        pte.setSum(su);

        account.addSpendingTotal(pte);
        // profitTotalEntityRepository.save(pte);
        return true;
    }

    @Transactional
    @Override
    public void deleteSpending(Long id) throws NotFoundException{
        spendingRepository.deleteById(id);
    }
}

