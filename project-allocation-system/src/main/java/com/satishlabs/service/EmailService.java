package com.satishlabs.service;

import com.satishlabs.model.Employee;
import com.satishlabs.model.ProjectAllocation;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendAllocationNotification(String s) {
    }

    /*private final JavaMailSender mailSender;

    public void sendAllocationEmail(Employee employee, ProjectAllocation allocation) {
        String subject = "Project Allocation Notification";
        String body = String.format("Dear %s,\n\nYou have been allocated to project %s from %s to %s.\n\nBest Regards,\nTeam",
                employee.getEmployeeName(), allocation.getProjectId(),
                allocation.getAllocationStartDate(), allocation.getAllocationEndDate());

        sendEmail(employee.getEmail(), subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        log.info("📧 Email sent successfully to {}", to);
    }*/
}

