package ua.student.courseworktry3.services.Interface;

import org.springframework.data.domain.Pageable;
import ua.student.courseworktry3.dto.AccountDTO;
import ua.student.courseworktry3.dto.ProfitDTO;
import ua.student.courseworktry3.dto.SpendingDTO;
import ua.student.courseworktry3.dto.TaskToNotifyDTO;


import java.util.Date;
import java.util.List;

public interface GeneralService {
    void addAccount(AccountDTO accountDTO, List<ProfitDTO> profitDTOS, List<SpendingDTO> spendingDTOS);
//    void addProfit(String email, ProfitDTO profitDTO);
//    List<ProfitDTO> getTasks(String email, Pageable pageable);
//    List<TaskToNotifyDTO> getTasksToNotify(Date now);
    Long count(String email);
    void delete(List<Long> idList);
}
