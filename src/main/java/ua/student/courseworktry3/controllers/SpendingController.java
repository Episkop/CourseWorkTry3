package ua.student.courseworktry3.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ua.student.courseworktry3.Exception.AlreadyExistException;
import ua.student.courseworktry3.Exception.DBIsEmptyException;
import ua.student.courseworktry3.Exception.NotFoundException;
import ua.student.courseworktry3.services.Interface.SpendingService;

@RestController
@RequestMapping("/spending")
public class SpendingController {

    private SpendingService spendingService;
    public SpendingController(SpendingService spendingService) {
        this.spendingService = spendingService;
    }

    @GetMapping("getSpending")
    public ResponseEntity getAll(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(spendingService.getAllSpending(email));
        } catch (DBIsEmptyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Data Base is empty!");
        }
    }

    @GetMapping("/total")
    public ResponseEntity to(OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(spendingService.getAllTotalSpending(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }

    @GetMapping("/one/{article}")
    public ResponseEntity getOne(@PathVariable String article, OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(spendingService.findByArticle(article, email));
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Not found!");
        }
    }

    @PostMapping("/save")
    public ResponseEntity addSpending(@RequestParam(required = true) String article, @RequestParam(required = false) Double january,
                                      @RequestParam(required = false) Double february, @RequestParam(required = false) Double march,
                                      @RequestParam(required = false) Double april, @RequestParam(required = false) Double may,
                                      @RequestParam(required = false) Double june, @RequestParam(required = false) Double july,
                                      @RequestParam(required = false) Double august, @RequestParam(required = false) Double september,
                                      @RequestParam(required = false) Double october, @RequestParam(required = false) Double november,
                                      @RequestParam(required = false) Double december, @RequestParam(required = false) Double year,
                                      OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            return ResponseEntity.ok(spendingService.addSpending(article, january, february, march, april, may, june, july, august,
                    september, october, november, december, year, email));
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
                                 @RequestParam(required = false) Double december, @RequestParam(required = false) Double year,
                                 OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            spendingService.updateSpending(article, january, february, march, april, may, june, july, august,
                    september, october, november, december, year, email);
            return ResponseEntity.ok("Success!");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Warning!!! Server request exception!");
        }
    }

    @DeleteMapping("/delete/{article}")
    public ResponseEntity delete(@PathVariable String article, OAuth2AuthenticationToken auth) {
        String email = getEmail(auth);
        try {
            spendingService.deleteSpending(email, article);
            return ResponseEntity.ok("Successes! The line was deleted! " + article);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Line not found!");
        }
    }

    private String getEmail(OAuth2AuthenticationToken auth) {
        return (String) auth.getPrincipal().getAttributes().get("email");
    }
}

