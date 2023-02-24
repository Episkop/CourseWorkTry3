package ua.student.courseworktry3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.ProfitDTO;
import ua.student.courseworktry3.model.Account;
import ua.student.courseworktry3.model.Profit;
import ua.student.courseworktry3.repos.AccountRepository;
import ua.student.courseworktry3.repos.ProfitRepository;
import ua.student.courseworktry3.services.Interface.ProfitService;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProfitServiceImpl implements ProfitService {

    private final ProfitRepository profitRepository;
    private final AccountRepository accountRepository;

    public ProfitServiceImpl(ProfitRepository profitRepository, AccountRepository accountRepository) {
        this.profitRepository = profitRepository;
        this.accountRepository = accountRepository;
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

    @Transactional
    @Override
    public boolean addProfit(ProfitDTO profitDTO, String email) throws AlreadyExistException {
        Account account = accountRepository.findByEmail(email);
        Profit profit = Profit.fromDTO(profitDTO);
        if (profitRepository.existsByArticle(profit.getArticle()))
           return  false;
        account.addProfit(profit);
            //throw new AlreadyExistException("Such" + profit.getArticle() + "already exist");
        accountRepository.save(account);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public Profit findByArticle(String article) throws NotFoundException {
        Profit profit = profitRepository.findByArticle(article);
        if (profit == null) {
            throw new NotFoundException("Didn`t find article " + profit.getArticle());
        }
        return profit;
    }

//    @Transactional
//    @Override
//    public List<ProfitTotalModel> getTotal(String email) throws DBIsEmptyException {
//        List<ProfitTotalModel> modelList = new ArrayList<>();
//        List<ProfitTotalEntity> list = profitTotalEntityRepository.findByUserEntityEmail(email);
//        if (list.isEmpty())
//            throw new DBIsEmptyException("Data Base Total is empty!");
//        list.forEach(x -> modelList.add(x.toModel()));
//        return modelList;
//    }

//    @Transactional
//    @Override
//    public boolean addProfitTotal(ProfitTotalModel profitTotalModel) throws AlreadyExistException{
//        if (profitTotalEntityRepository.existsByArticle(profitTotalModel.getArticle()))
//            return false;
//        ProfitTotalEntity profitTotalEntity = ProfitTotalEntity.fromModel(profitTotalModel);
//        profitTotalEntityRepository.save(profitTotalEntity);
//        return true;
//    }

//    @Transactional
//    @Override
//    public boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
//                                Double june, Double july, Double august, Double september, Double october, Double november,
//                                Double december, Double sum) throws NotFoundException {
//        ProfitEntity profit = profitRepository.findByArticle(article);
//        if (profit == null) {
//            throw new NotFoundException("Such " + article + " don`t found");
//        }
//        if (january != null)
//            profit.setJanuary(january);
//        if (february != null)
//            profit.setFebruary(february);
//        if (march != null)
//            profit.setMarch(march);
//        if (april != null)
//            profit.setApril(april);
//        if (may != null)
//            profit.setMay(may);
//        if (june != null)
//            profit.setJune(june);
//        if (july != null)
//            profit.setJuly(july);
//        if (august != null)
//            profit.setAugust(august);
//        if (september != null)
//            profit.setSeptember(september);
//        if (october != null)
//            profit.setOctober(october);
//        if (november != null)
//            profit.setNovember(november);
//        if (december != null)
//            profit.setDecember(december);
//        if (sum != null)
//            profit.setSum(sum);
//        //TODO
//        profitRepository.sumProfitLine(january, february, march, april, may, june, july, august,
//                september, october, november, december,article);
//        profitRepository.save(profit);
//
//        ProfitTotalEntity pte = profitTotalEntityRepository.findByArticle("total");
//        Double jan = profitRepository.totalJan();
//        pte.setJanuary(jan);
//        Double feb = profitRepository.totalFeb();
//        pte.setFebruary(feb);
//        Double mar = profitRepository.totalMar();
//        pte.setMarch(mar);
//        Double apr = profitRepository.totalApr();
//        pte.setApril(apr);
//        Double ma = profitRepository.totalMay();
//        pte.setMay(ma);
//        Double jun = profitRepository.totalJun();
//        pte.setJune(jun);
//        Double jul = profitRepository.totalJul();
//        pte.setJuly(jul);
//        Double aug = profitRepository.totalAug();
//        pte.setAugust(aug);
//        Double sep = profitRepository.totalSep();
//        pte.setSeptember(sep);
//        Double oct = profitRepository.totalOct();
//        pte.setOctober(oct);
//        Double nov = profitRepository.totalNov();
//        pte.setNovember(nov);
//        Double dec = profitRepository.totalDec();
//        pte.setDecember(dec);
//        Double su = profitRepository.totalSum();
//        pte.setSum(su);
//
//        profitTotalEntityRepository.save(pte);
//        return true;
//    }

    @Transactional
    @Override
    public void deleteProfit(Long id) throws NotFoundException{
        profitRepository.deleteById(id);
    }


}
