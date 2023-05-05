package ua.student.courseworktry3.services.Interface;


import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.ProfitDTO;
import ua.student.courseworktry3.dto.ProfitTotalDTO;
import ua.student.courseworktry3.model.Profit;

import java.util.List;

public interface ProfitService {
    List<ProfitDTO> getAllProfit (String email) throws DBIsEmptyException;

    @Transactional
    boolean addProfit(String article, Double january, Double february, Double march, Double april, Double may,
                      Double june, Double july, Double august, Double september, Double october, Double november,
                      Double december, Double sum, String email) throws AlreadyExistException;

    ProfitDTO findByArticle(String article) throws NotFoundException;

    List<ProfitTotalDTO> getAllTotalProfit(String email) throws DBIsEmptyException;

//    boolean addProfitTotal(ProfitTotalModel profitTotalModel) throws AlreadyExistException;

    boolean updateProfit(String article, Double january, Double february, Double march, Double april, Double may,
                         Double june, Double july, Double august, Double september, Double october, Double november,
                         Double december, Double sum,String email) throws NotFoundException;



    void deleteProfit (Long id) throws NotFoundException;


}
