package ua.student.courseworktry3.mail;

import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

//    private final EmailService emailService;
//    private final GeneralService generalService;
//
//    public EmailScheduler(EmailService emailService, GeneralService generalService) {
//        this.emailService = emailService;
//        this.generalService = generalService;
//    }
//
//    @Scheduled(fixedDelay = 60000)
//    public void sendNotifications() {
//        List<TaskToNotifyDTO> tasks = generalService.getTasksToNotify(new Date());
//        tasks.forEach((task) -> emailService.sendMessage(task));
//    }
}
