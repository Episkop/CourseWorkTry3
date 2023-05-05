package ua.student.courseworktry3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.dto.*;
import ua.student.courseworktry3.model.*;
import ua.student.courseworktry3.repos.AccountRepository;
import ua.student.courseworktry3.repos.ProfitRepository;
import ua.student.courseworktry3.services.Interface.GeneralService;


import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {

    private final AccountRepository accountRepository;
    private final ProfitRepository profitRepository;

    public GeneralServiceImpl(AccountRepository accountRepository, ProfitRepository profitRepository) {
        this.accountRepository = accountRepository;
        this.profitRepository = profitRepository;
    }

    // DB -> E(20) -> R -> S <-> DTO -> C -> V(5) / JSON

    @Transactional
    @Override
    public void addAccount(AccountDTO accountDTO, List<ProfitDTO> profitDTOS, List<SpendingDTO> spendingDTOS,
                           ProfitTotalDTO totalProfitDTO, SpendingTotalDTO totalSpendingDTO) {
        if (accountRepository.existsByEmail(accountDTO.getEmail()))
            return; // do nothing

        Account account = Account.fromDTO(accountDTO);

        profitDTOS.forEach((x) -> {
            Profit profit = Profit.fromDTO(x);
            account.addProfit(profit);
        });
        spendingDTOS.forEach((x) -> {
            Spending spending = Spending.fromDTO(x);
            account.addSpending(spending);
        });

        ProfitTotal profitTotal = ProfitTotal.fromDTO(totalProfitDTO);
        account.addProfitTotal(profitTotal);

        SpendingTotal spendingTotal = SpendingTotal.fromDTO(totalSpendingDTO);
        account.addSpendingTotal(spendingTotal);

        accountRepository.save(account);
    }

//    @Transactional
//    @Override
//    public void addProfit(String email, ProfitDTO profitDTO) {
//        Account account = accountRepository.findByEmail(email);
//        Profit profit = Profit.fromDTO(profitDTO);
//
//        account.addProfit(profit);
//
//        accountRepository.save(account);
//    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<ProfitDTO> getTasks(String email, Pageable pageable) {
//        List<ProfitDTO> result = new ArrayList<>();
//        List<Profit> profits = profitRepository.findByAccountEmail(email, pageable);
//
//        profits.forEach((x) -> result.add(x.toDTO()));
//        return result;
//    }

    /*

    19:57:31
    [19:57:00; 19:58:00)

     */

//    @Transactional(readOnly = true)
//    @Override
//    public List<TaskToNotifyDTO> getTasksToNotify(Date now) {
//        Calendar calendar = Calendar.getInstance();
//
//        calendar.setTime(now);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date from = calendar.getTime();
//
//        calendar.add(Calendar.MINUTE, 1);
//        Date to = calendar.getTime();
//
//        return taskRepository.findTasksToNotify(from, to);
//    }

    @Transactional(readOnly = true)
    @Override
    public Long count(String email) {
        return profitRepository.countByAccountEmail(email);
    }

    @Transactional
    @Override
    public void delete(List<Long> idList) {
        idList.forEach((x) -> profitRepository.deleteById(x));
    }
}
