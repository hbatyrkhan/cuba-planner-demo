package com.company.planner2.service;

import com.company.planner2.entity.Session;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Service(SessionService.NAME)
public class SessionServiceBean implements SessionService {
    @Inject
    private DataManager dataManager;

    @Override
    public boolean valid(Session session) {
        List<Session> sessions = dataManager.loadValue("select s from planner2_Session s " +
                "where s.id <> :sessionId", Session.class)
                .parameter("sessionId", session.getId())
                .list();
        for(Session cur_session : sessions) {
            if(cur_session.overlaps(session)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Session rescheduleSession(Session session, LocalDateTime newStartDate) {
        LocalDateTime startDate = session.getStartDate();
        session.setStartDate(newStartDate);
        if(!valid(session)) {
            session.setStartDate(startDate);
            return session;
        }
        return dataManager.commit(session);
    }

    @Override
    public Session rescheduleSession(Session session, LocalDateTime newStartDate, Integer newDuration) {
        LocalDateTime startDate = session.getStartDate();
        Integer duration = session.getDuration();
        session.setStartDate(newStartDate);
        session.setDuration(newDuration);
        if(!valid(session)) {
            session.setStartDate(startDate);
            session.setDuration(duration);
            return session;
        }
        return dataManager.commit(session);
    }
}