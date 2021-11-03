package com.company.jmixpm.screen.sandbox;

import com.company.dwa.ColorButton;
import com.company.jmixpm.widgets.QuillEditor;
import com.vaadin.ui.Layout;
import io.jmix.ui.Notifications;
import io.jmix.ui.Notifications.NotificationType;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.VBoxLayout;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Sandbox")
@UiDescriptor("sandbox.xml")
@Route("sandbox")
public class Sandbox extends Screen {
//    @Autowired
//    private VBoxLayout stepperWrapper;
//    @Autowired
//    private JavaScriptComponent quill;
    @Autowired
    private VBoxLayout quillBox;
    @Autowired
    private Notifications notifications;

    private QuillEditor quillEditor;

    @Subscribe
    public void onInit(InitEvent event) {
        quillEditor = new QuillEditor();
        quillEditor.setWidth("400px");
        quillEditor.setHeight("200px");
        quillEditor.setValueChangeHandler(valueChangeEvent ->
                notifications.create()
                        .withCaption(valueChangeEvent.getValue())
                        .withDescription("User originated: " + valueChangeEvent.isUserOriginated())
                        .withType(NotificationType.TRAY)
                        .show());

        quillBox.unwrap(Layout.class).addComponent(quillEditor);


        
        ColorButton button = new ColorButton();
        button.setCaption("Button");
        button.setColor("#C6D1EC");

        getWindow().unwrap(Layout.class).addComponent(button);
    }

    @Subscribe("clearQuillEditorBtn")
    public void onClearQuillEditorBtnClick(Button.ClickEvent event) {
        quillEditor.clear();
    }

    @Subscribe("randomValueBtn")
    public void onRandomValueBtnClick(Button.ClickEvent event) {
        quillEditor.setValue(RandomStringUtils.randomAlphabetic(30));
    }

    /*@Subscribe
    public void onInit(InitEvent event) {
        QuillState state = new QuillState();
        state.theme = "snow";
        state.placeholder = "Compose an epic...";

        quill.setState(state);

        quill.addFunction("valueChanged", javaScriptCallbackEvent -> {
            String value = javaScriptCallbackEvent.getArguments().getString(0);
            notifications.create()
                    .withCaption(value)
                    .withPosition(Position.BOTTOM_RIGHT)
                    .show();
        });


        IntStepper stepper = new IntStepper("Stepper");
        stepper.setMinValue(0);
        stepper.setMaxValue(20);

        stepperWrapper.unwrap(Layout.class).addComponent(stepper);


        final CustomButton button = new CustomButton();
        button.setCaption("Button");
        button.setColor("#C6D1EC");

        stepperWrapper.unwrap(Layout.class).addComponent(button);
    }

    @Subscribe("clearQuillBtn")
    public void onClearQuillBtnClick(Button.ClickEvent event) {
        quill.callFunction("deleteText");
    }

    static class QuillState {
        public String theme;
        public String placeholder;
    }*/
}