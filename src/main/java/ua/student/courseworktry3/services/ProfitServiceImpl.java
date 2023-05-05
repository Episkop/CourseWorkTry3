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
        List<Profit> list = profitRepository.findByAccountEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProfitTotalDTO> getAllTotalProfit(String email) throws DBIsEmptyException {
        List<ProfitTotalDTO> modelList = new ArrayList<>();
        List<ProfitTotal> list = profitTotalRepository.findByAccountPTEmail(email);
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base Total is empty!");
        list.forEach(x -> modelList.add(x.toDTO()));
        return modelList;
    }
//    @Transactional(readOnly = true)
//    @Override
//    public List<ProfitTotalDTO> getAllTotalProfit() throws DBIsEmptyException {
//        List<ProfitTotalDTO> modelList = new ArrayList<>();
//        List<ProfitTotal> list = profitTotalRepository.findAll();
//        if (list.isEmpty())
//            throw new DBIsEmptyException("Data Base is empty!");
//        list.forEach(x -> modelList.add(x.toDTO()));
//        return modelList;
//    }

    @Transactional
    @Override
    public boolean addProfit(String article, Double january, Double february, Double march, Double april, Double may,
                             Double june, Double july, Double august, Double september, Double october, Double november,
                             Double december, Double sum, String email) throws AlreadyExistException {
        Account account = accountRepository.findByEmail(email);
        if (profitRepository.existsByArticle(article))
            return false;
        ProfitDTO dto = new ProfitDTO(article, january, february, march, april, may, june, july, august,
                september, october, november, december, sum);
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
        if (sum == null)
            dto.setSum(sum);
        account.addProfit(Profit.fromDTO(dto));
        return true;
    }
//    @Transactional
//    @Override
//    public boolean addProfitTotal(ProfitTotalDTO profitTotalDTO, String email) {
//        Account account = accountRepository.findByEmail(email);
//        if (profitTotalRepository.existsByArticle(profitTotalDTO.getArticle()))
//            return false;
//        ProfitTotal profitTotal = ProfitTotal.fromDTO(profitTotalDTO);
//        profitTotalRepository.save(profitTotal);
//        return true;
//    }

    @Transactional(readOnly = true)
    @Override
    public ProfitDTO findByArticle(String article) throws NotFoundException {
        Profit profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Didn`t find article " + profit.getArticle());
        }
        return profit.toDTO();
    }

    @Transactional
    @Override
    public boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                                Double june, Double july, Double august, Double september, Double october, Double november,
                                Double december, Double sum, String email) throws NotFoundException {
        Account account = accountRepository.findByEmail(email);
        if (profitRepository.existsByArticle(article))
            return false;
//        ProfitDTO profit = profitRepository.findByArticle(article);
//        if (profit == null) {
//            throw new NotFoundException("Such " + article + " don`t found");
//        }
        ProfitDTO profit = new ProfitDTO(article, january, february, march, april, may, june, july, august,
                september, october, november, december, sum);
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
        if (sum != null)
            profit.setSum(sum);
        //TODO
        profitRepository.sumProfitLine(january, february, march, april, may, june, july, august,
                september, october, november, december, article);
        account.addProfit(Profit.fromDTO(profit));
//        profitRepository.save(profit);

        ProfitTotal pte = profitTotalRepository.findByArticle("total");
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
        Double su = profitRepository.totalSum();
        pte.setSum(su);

        account.addProfitTotal(pte);
        // profitTotalEntityRepository.save(pte);
        return true;
    }

    @Transactional
    @Override
    public void deleteProfit(Long id) throws NotFoundException {
        profitRepository.deleteById(id);
    }


}
