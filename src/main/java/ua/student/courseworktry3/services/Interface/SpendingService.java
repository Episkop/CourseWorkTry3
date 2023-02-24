package ua.student.courseworktry3.services.Interface;

import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.SpendingDTO;
import ua.student.courseworktry3.model.Spending;


import java.util.List;

public interface SpendingService {

    List<Spending> getAllSpending() throws DBIsEmptyException;


    boolean addSpending(SpendingDTO spendingDTO, String email) throws AlreadyExistException;


    Spending findByArticle(String article) throws NotFoundException;


//    boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
//                           Double june, Double july, Double august, Double september, Double october, Double november,
//                           Double december) throws NotFoundException;


    void deleteSpending(Long id) throws NotFoundException;
}
