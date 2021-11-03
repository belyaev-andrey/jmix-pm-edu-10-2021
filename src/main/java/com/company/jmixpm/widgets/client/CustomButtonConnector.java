package com.company.jmixpm.widgets.client;

import com.company.jmixpm.widgets.CustomButton;
import com.google.gwt.dom.client.Style;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.shared.ui.Connect;
import io.jmix.ui.widget.client.button.JmixButtonConnector;

@Connect(CustomButton.class)
public class CustomButtonConnector extends JmixButtonConnector {

    @Override
    public CustomButtonState getState() {
        return (CustomButtonState) super.getState();
    }

    @Override
    public void onStateChanged(StateChangeEvent event) {
        super.onStateChanged(event);

        if (event.hasPropertyChanged("color")) {
            Style style = getWidget().getElement().getStyle();
            style.setBackgroundColor(getState().color);
            style.setBackgroundImage("none");
        }
    }
}
