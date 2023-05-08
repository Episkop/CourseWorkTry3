package ua.student.courseworktry3.services.Interface;

import org.springframework.transaction.annotation.Transactional;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.SpendingDTO;
import ua.student.courseworktry3.dto.SpendingTotalDTO;


import java.util.List;

public interface SpendingService {


    List<SpendingDTO> getAllSpending(String email) throws DBIsEmptyException;


    boolean addSpending(String article, Double january, Double february, Double march, Double april, Double may,
                        Double june, Double july, Double august, Double september, Double october, Double november,
                        Double december, Double sum, String email) throws AlreadyExistException;

    SpendingDTO findByArticle(String article, String email) throws NotFoundException, DBIsEmptyException;

    List<SpendingTotalDTO> getAllTotalSpending(String email) throws DBIsEmptyException;

    boolean updateSpending(String article, Double january, Double february, Double march, Double april, Double may,
                           Double june, Double july, Double august, Double september, Double october, Double november,
                           Double december, Double sum, String email) throws NotFoundException;

    void countSum(String email);

    void deleteSpending(String email, String article) throws NotFoundException;
}
