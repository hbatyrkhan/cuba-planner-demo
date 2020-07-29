package com.company.planner2.entity;

import com.company.planner2.entity.validator.CheckSessionIntersection;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.core.global.validation.groups.UiCrossFieldChecks;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.time.LocalDateTime;

@CheckSessionIntersection(groups = {Default.class, UiCrossFieldChecks.class})
@NamePattern("%s|topic")
@Table(name = "PLANNER2_SESSION")
@Entity(name = "planner2_Session")
public class Session extends StandardEntity {
    private static final long serialVersionUID = 966620827257867949L;

    @NotNull
    @Column(name = "TOPIC", nullable = false)
    protected String topic;

    @NotNull
    @Column(name = "START_DATE", nullable = false)
    protected LocalDateTime startDate;

    @Column(name = "DURATION")
    protected @Positive Integer duration;

    @NotNull
    @OnDelete(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SPEAKER_ID")
    protected Speaker speaker;

    @Override
    public String toString() {
        return "Session{" +
                "topic='" + topic + '\'' +
                ", startDate=" + startDate +
                ", duration=" + duration +
                ", endDate=" + getEndDate() + '\'' +
                '}';
    }

    @Lob
    @Column(name = "DESCRIPTION")
    protected String description;

    @Transient
    @MetaProperty(related = {"description", "speaker"})
    public String getPoster() {
        return topic != null && speaker != null ? topic + " by " + speaker.getFirstName() : null;
    }
    private boolean isEqualOrBefore(LocalDateTime date1, LocalDateTime date2) {
        return date1.isBefore(date2) || date1.isEqual(date2);
    }
    public boolean overlaps(Session other) {
        if(other.getStartDate().isBefore(getStartDate())) {
            return other.overlaps(this);
        }
        if(isEqualOrBefore(getStartDate(), other.getEndDate()) &&
                isEqualOrBefore(other.getEndDate(), getEndDate())) {
            return true;
        }
        return isEqualOrBefore(getStartDate(), other.getStartDate()) &&
                isEqualOrBefore(other.getStartDate(), getEndDate());
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Transient
    @MetaProperty(related = {"startDate", "duration"})
    public LocalDateTime getEndDate() {
        return startDate != null && duration != null ? startDate.plusMinutes(duration) : null;
    }
}