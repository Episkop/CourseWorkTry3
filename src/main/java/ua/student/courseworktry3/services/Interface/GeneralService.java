package ua.student.courseworktry3.services.Interface;

import ua.student.courseworktry3.dto.*;


import java.util.List;

public interface GeneralService {
    void addAccount(AccountDTO accountDTO, List<ProfitDTO> profitDTOS, List<SpendingDTO> spendingDTOS, ProfitTotalDTO totalDTO, SpendingTotalDTO totalSpendingDTO);
//    void addProfit(String email, ProfitDTO profitDTO);
//    List<ProfitDTO> getTasks(String email, Pageable pageable);
//    List<TaskToNotifyDTO> getTasksToNotify(Date now);
    Long count(String email);
    void delete(List<Long> idList);
}
