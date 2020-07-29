package com.company.planner2.service;

import com.company.planner2.entity.Session;

import java.time.LocalDateTime;

public interface SessionService {
    String NAME = "planner2_SessionService";
    public Session rescheduleSession(Session session, LocalDateTime newStartDate);
    public Session rescheduleSession(Session session, LocalDateTime newStartDate, Integer duration);
    public boolean valid(Session session);
}