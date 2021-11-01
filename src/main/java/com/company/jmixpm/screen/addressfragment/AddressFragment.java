package com.company.jmixpm.screen.addressfragment;

import io.jmix.ui.Notifications;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("AddressFragment")
@UiDescriptor("address-fragment.xml")
public class AddressFragment extends ScreenFragment {

    @Autowired
    private Notifications notifications;

    @Subscribe(target = Target.PARENT_CONTROLLER)
    private void onBeforeShowHost(Screen.BeforeShowEvent event) {
        notifications.create()
                .withCaption("[Parent] BeforeShowEvent")
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }
}