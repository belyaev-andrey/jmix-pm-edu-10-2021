package com.company.jmixpm.component;

import io.jmix.ui.component.Field;

public interface Stepper extends Field<Integer> {

    String NAME = "stepper";

    boolean isManualInputAllowed();
    void setManualInputAllowed(boolean value);

    boolean isMouseWheelEnabled();
    void setMouseWheelEnabled(boolean value);

    int getStepAmount();
    void setStepAmount(int amount);

    int getMaxValue();
    void setMaxValue(int maxValue);

    int getMinValue();
    void setMinValue(int minValue);
}
