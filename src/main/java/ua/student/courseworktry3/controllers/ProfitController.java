package ua.student.courseworktry3.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.dto.ProfitDTO;
import ua.student.courseworktry3.services.Interface.ProfitService;


@RestController
@RequestMapping("/profit")
public class ProfitController {

    private ProfitService profitService;

    public ProfitController(ProfitService profitService) {
        this.profitService = profitService;
    }

    @GetMapping("getProfit")
    public ResponseEntity getAll(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(profitService.getAllProfit(email));
        } catch (DBIsEmptyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Data Base is empty!");
        }
    }

    @GetMapping("/one/{article}")
    public ResponseEntity getOne(@PathVariable String article,OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(profitService.findByArticle(article));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not found!");
        }
    }

    @GetMapping("/total")
    public ResponseEntity to (OAuth2AuthenticationToken auth){
        String email = getEmail(auth);
        try{
            return ResponseEntity.ok(profitService.getAllTotalProfit(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }


    @PostMapping("/save")
    public ResponseEntity saveProfit(@RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                 @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                 @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                 @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                 @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                 @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                 @RequestParam(required = false) Double december,@RequestParam(required = false) Double sum,
                                     OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(profitService.addProfit(article, january, february, march, april, may, june, july, august,
                    september, october, november, december,sum,email));
        } catch (AlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Line didn`t save");
        }

    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                 @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                 @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                 @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                 @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                 @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                 @RequestParam(required = false) Double december,@RequestParam(required = false) Double sum,
                                 OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            profitService.updateProfit(article, january, february, march, april, may, june, july, august,
                    september, october, november, december,sum ,email);
            return ResponseEntity.ok("Success!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            profitService.deleteProfit(id);
            return ResponseEntity.ok("Successes! The line was deleted! " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Line not found!");
        }
    }
    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }
}
