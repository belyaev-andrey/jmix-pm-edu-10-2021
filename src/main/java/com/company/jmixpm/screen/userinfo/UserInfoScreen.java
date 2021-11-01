package com.company.jmixpm.screen.userinfo;

import com.company.jmixpm.app.PostService;
import com.company.jmixpm.entity.UserInfo;
import com.google.common.collect.ImmutableMap;
import io.jmix.core.LoadContext;
import io.jmix.ui.action.Action;
import io.jmix.ui.model.InstanceLoader;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.navigation.UrlIdSerializer;
import io.jmix.ui.navigation.UrlParamsChangedEvent;
import io.jmix.ui.navigation.UrlRouting;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import static io.jmix.core.common.util.Preconditions.checkNotNullArgument;

@UiController("UserInfoScreen")
@UiDescriptor("user-info-screen.xml")
@Route("user-info")
@DialogMode(forceDialog = true, width = "AUTO", height = "AUTO")
public class UserInfoScreen extends Screen {

    @Autowired
    private PostService postService;
    @Autowired
    private UrlRouting urlRouting;
    @Autowired
    private InstanceLoader<UserInfo> userInfoDl;

    private Long userId;

    public void setUserId(Long userId) {
        checkNotNullArgument(userId);
        this.userId = userId;
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        String serializeId = UrlIdSerializer.serializeId(userId);
        urlRouting.replaceState(this, ImmutableMap.of("id", serializeId));
    }

    @Subscribe
    public void onUrlParamsChanged(UrlParamsChangedEvent event) {
        String serializedId = event.getParams().get("id");
        userId = (Long) UrlIdSerializer.deserializeId(Long.class, serializedId);
        userInfoDl.load();
    }

    @Install(to = "userInfoDl", target = Target.DATA_LOADER)
    private UserInfo userInfoDlLoadDelegate(LoadContext<UserInfo> loadContext) {
        return postService.fetchUserInfo(userId);
    }

    @Subscribe("windowCLose")
    public void onWindowCLose(Action.ActionPerformedEvent event) {
        closeWithDefaultAction();
    }
}