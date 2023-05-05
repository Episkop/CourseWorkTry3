package ua.student.courseworktry3.services.Interface;

import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.SpendingDTO;
import ua.student.courseworktry3.dto.SpendingTotalDTO;
import ua.student.courseworktry3.model.Spending;


import java.util.List;

public interface SpendingService {


    List<SpendingDTO> getAllSpending(String email) throws DBIsEmptyException;


    boolean addSpending(String article, Double january, Double february, Double march, Double april, Double may,
                        Double june, Double july, Double august, Double september, Double october, Double november,
                        Double december, Double sum, String email) throws AlreadyExistException;


    Spending findByArticle(String article) throws NotFoundException;



    List<SpendingTotalDTO> getTotal(String email) throws DBIsEmptyException;

    boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                           Double june, Double july, Double august, Double september, Double october, Double november,
                           Double december, Double sum, String email) throws NotFoundException;

    void deleteSpending(Long id) throws NotFoundException;
}
