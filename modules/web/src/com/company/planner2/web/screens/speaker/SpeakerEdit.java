package com.company.planner2.web.screens.speaker;

import com.haulmont.cuba.gui.screen.*;
import com.company.planner2.entity.Speaker;

@UiController("planner2_Speaker.edit")
@UiDescriptor("speaker-edit.xml")
@EditedEntityContainer("speakerDc")
@LoadDataBeforeShow
public class SpeakerEdit extends StandardEditor<Speaker> {
}