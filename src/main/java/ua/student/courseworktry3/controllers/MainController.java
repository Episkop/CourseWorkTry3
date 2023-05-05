package ua.student.courseworktry3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.dto.AccountDTO;
import ua.student.courseworktry3.dto.PageCountDTO;
import ua.student.courseworktry3.dto.ProfitDTO;
import ua.student.courseworktry3.dto.results.BadRequestResult;
import ua.student.courseworktry3.dto.results.ResultDTO;
import ua.student.courseworktry3.dto.results.SuccessResult;
import ua.student.courseworktry3.services.Interface.GeneralService;
import ua.student.courseworktry3.services.Interface.ProfitService;

import java.util.Arrays;
import java.util.Map;

@RestController
public class MainController {
    
    private static final int PAGE_SIZE = 5;

    private final GeneralService generalService;
    private final ProfitService profitService;
    public MainController(GeneralService generalService, ProfitService profitService) {
        this.generalService = generalService;
        this.profitService = profitService;
    }

    @GetMapping("account")
    public AccountDTO account(OAuth2AuthenticationToken auth) {
        Map<String, Object> attrs = auth.getPrincipal().getAttributes();

        String email = (String) attrs.get("email");
        String name = (String) attrs.get("name");
        String pictureUrl = (String) attrs.get("picture");

        return AccountDTO.of(email, name, pictureUrl);
    }

    @GetMapping("count")
    public PageCountDTO count(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        return PageCountDTO.of(generalService.count(email), PAGE_SIZE);
    }

//    @GetMapping("tasks")
//    public List<ProfitDTO> profit(OAuth2AuthenticationToken auth, @RequestParam(required = false, defaultValue = "0") Integer page) {
//        String email = getEmail(auth);
//
//        return generalService.getTasks(email, PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "id"));
//    }

    @PostMapping("add")
    public ResponseEntity<ResultDTO> addTask(OAuth2AuthenticationToken auth, @RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                 @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                 @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                 @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                 @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                 @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                 @RequestParam(required = false) Double december,@RequestParam(required = false) Double sum) throws AlreadyExistException {
        String email = getEmail(auth);
        profitService.addProfit(article, january, february, march, april, may, june, july, august,
                september, october, november, december,sum, email);

        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<ResultDTO> delete(@RequestParam(name = "toDelete[]", required = false) Long[] idList) {
        generalService.delete(Arrays.asList(idList));
        return new ResponseEntity<>(new SuccessResult(), HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResultDTO> handleException() {
        return new ResponseEntity<>(new BadRequestResult(), HttpStatus.BAD_REQUEST);
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }
}
