package com.company.jmixpm.widgets;

import com.company.jmixpm.widgets.client.CustomButtonState;
import io.jmix.ui.widget.JmixButton;

import java.util.Objects;

public class CustomButton extends JmixButton {

    @Override
    protected CustomButtonState getState() {
        return (CustomButtonState) super.getState();
    }

    @Override
    protected CustomButtonState getState(boolean markAsDirty) {
        return (CustomButtonState) super.getState(markAsDirty);
    }

    public String getColor() {
        return getState(false).color;
    }

    public void setColor(String color) {
        if (!Objects.equals(getState(false).color, color)) {
            getState().color = color;
        }
    }
}
