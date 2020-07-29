package com.company.planner2.web.screens.speaker;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner2.entity.Speaker;

@UiController("planner2_Speaker.browse")
@UiDescriptor("speaker-browse.xml")
@LookupComponent("speakersTable")
@LoadDataBeforeShow
public class SpeakerBrowse extends StandardLookup<Speaker> {
}