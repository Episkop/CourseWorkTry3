package ua.student.courseworktry3.mail;

import ua.student.courseworktry3.dto.TaskToNotifyDTO;

public interface EmailService {
    void sendMessage(TaskToNotifyDTO task);
}
