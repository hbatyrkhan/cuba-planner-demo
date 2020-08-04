package com.company.planner2.delegate;

import com.haulmont.cuba.security.entity.User;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompleteBProcDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(CompleteBProcDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        User manager = (User)execution.getVariable("manager");
        String email = manager.getEmail();
        String text = (String)execution.getVariable("comment");
        log.info(manager.toString());
        log.info(text);
        sendEmail(email, text);
    }
    private void sendEmail(String address, String text) {
        log.info("An email being sent to {} with text: {}", address, text);
    }
}
