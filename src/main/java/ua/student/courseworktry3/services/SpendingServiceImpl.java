package ua.student.courseworktry3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.SpendingDTO;
import ua.student.courseworktry3.model.Account;
import ua.student.courseworktry3.model.Spending;
import ua.student.courseworktry3.repos.AccountRepository;
import ua.student.courseworktry3.repos.SpendingRepository;
import ua.student.courseworktry3.services.Interface.SpendingService;

import java.util.List;


@Service
public class SpendingServiceImpl implements SpendingService {
    private final SpendingRepository spendingRepository;
    private final AccountRepository accountRepository;

    public SpendingServiceImpl(SpendingRepository spendingRepository, AccountRepository accountRepository) {
        this.spendingRepository = spendingRepository;
        this.accountRepository = accountRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Spending> getAllSpending() throws DBIsEmptyException {
        List<Spending> list = spendingRepository.findAll();
        if (list.isEmpty())
            throw new DBIsEmptyException("Data Base is empty!");
        return list;
    }

    @Transactional
    @Override
    public boolean addSpending(SpendingDTO spendingDTO, String email) throws AlreadyExistException {
        Account account = accountRepository.findByEmail(email);
        Spending spending = Spending.fromDTO(spendingDTO);
        if (spendingRepository.existsByArticle(spending.getArticle()))
            return  false;
        account.addSpending(spending);
        //throw new AlreadyExistException("Such" + profit.getArticle() + "already exist");
        accountRepository.save(account);
        return true;
    }
//    @Transactional
//    @Override
//    public boolean addSpending(Spending spending) throws AlreadyExistException {
//        if (spendingRepository.existsByArticle(spending.getArticle()))
//            return  false;
//        //throw new AlreadyExistException("Such" + profit.getArticle() + "already exist");
//        spendingRepository.save(spending);
//        return true;
//    }

//    @Transactional
//    @Override
//    public void countLineSum(String article, Double january, Double february, Double march, Double april, Double may,
//                             Double june, Double july, Double august, Double september, Double october, Double november,
//                             Double december){
//    }

    @Transactional(readOnly = true)
    @Override
    public Spending findByArticle(String article) throws NotFoundException {
        Spending spending = spendingRepository.findByArticle(article);
        if (spending == null) {
            throw new NotFoundException("Didn`t find article " + spending.getArticle());
        }
        return spending;
    }


//    public String getIfExists(String article) throws NotFoundException {
//        if (profitRepository.existsByArticle(article)) {
//            throw new NotFoundException("Not exist!");
//        }
//        return "true";
//    }

//    @Transactional
//    @Override
//    public boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
//                                Double june, Double july, Double august, Double september, Double october, Double november,
//                                Double december) throws NotFoundException {
//        SpendingEntity spending = spendingRepository.findByArticle(article);
//        if (spending == null) {
//            throw new NotFoundException("Such " + article + " don`t found");
//        }
//        if (january != null)
//            spending.setJanuary(january);
//        if (february != null)
//            spending.setFebruary(february);
//        if (march != null)
//            spending.setMarch(march);
//        if (april != null)
//            spending.setApril(april);
//        if (may != null)
//            spending.setMay(may);
//        if (june != null)
//            spending.setJune(june);
//        if (july != null)
//            spending.setJuly(july);
//        if (august != null)
//            spending.setAugust(august);
//        if (september != null)
//            spending.setSeptember(september);
//        if (october != null)
//            spending.setOctober(october);
//        if (november != null)
//            spending.setNovember(november);
//        if (december != null)
//            spending.setDecember(december);
//
//        //TODO
//        spendingRepository.save(spending);
//        return true;
//    }

    @Transactional
    @Override
    public void deleteSpending(Long id) throws NotFoundException{
        spendingRepository.deleteById(id);
    }
}

