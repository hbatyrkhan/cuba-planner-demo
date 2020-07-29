package com.company.planner2.web.screens.session;

import com.company.planner2.service.SessionService;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Calendar;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.planner2.entity.Session;

import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDateTime;

@UiController("planner2_Session.browse")
@UiDescriptor("session-browse.xml")
@LookupComponent("sessionsTable")
@LoadDataBeforeShow
public class SessionBrowse extends StandardLookup<Session> {
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private CollectionContainer<Session> sessionsDc;
    @Inject
    private SessionService sessionService;

    @Subscribe("sessionsCalendar")
    public void onSessionsCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        screenBuilders.editor(Session.class, this)
                .withScreenClass(SessionEdit.class)
                .editEntity((Session)event.getEntity())
                .withOpenMode(OpenMode.DIALOG)
                .withAfterCloseListener(afterCloseEvent -> {
                    CloseAction closeAction = afterCloseEvent.getCloseAction();
                    if(closeAction.equals(WINDOW_COMMIT_AND_CLOSE_ACTION)) {
                        Session editedEntity = afterCloseEvent.getScreen().getEditedEntity();
                        sessionsDc.replaceItem(editedEntity);
                    }
                }).show();
    }
    @Subscribe("sessionsCalendar")
    public void onSessionsCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        Session session = sessionService.rescheduleSession((Session) event.getEntity(), event.getNewStart());
        sessionsDc.replaceItem(session);
    }

    @Subscribe("sessionsCalendar")
    public void onSessionsCalendarCalendarEventResize(Calendar.CalendarEventResizeEvent<LocalDateTime> event) {
        Integer duration = Math.toIntExact(Duration.between(event.getNewStart(), event.getNewEnd()).toMinutes());
        Session session = sessionService.rescheduleSession((Session) event.getEntity(), event.getNewStart(), duration);
        sessionsDc.replaceItem(session);
    }
}