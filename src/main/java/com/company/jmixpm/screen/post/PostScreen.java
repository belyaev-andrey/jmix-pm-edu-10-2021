package com.company.jmixpm.screen.post;

import com.company.jmixpm.app.PostService;
import com.company.jmixpm.entity.Post;
import com.company.jmixpm.entity.UserInfo;
import com.company.jmixpm.screen.userinfo.UserInfoScreen;
import io.jmix.core.LoadContext;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Table;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@UiController("PostScreen")
@UiDescriptor("post-screen.xml")
@Route("posts")
public class PostScreen extends Screen {

    @Autowired
    private Table<Post> postsTable;

    @Autowired
    private PostService postService;

    @Install(to = "postsDl", target = Target.DATA_LOADER)
    private List<Post> postsDlLoadDelegate(LoadContext<Post> loadContext) {
        Post[] posts = postService.fetchPosts();
        return Arrays.asList(posts);
    }

    @Install(to = "userInfoScreenFacet", subject = "screenConfigurer")
    private void userInfoScreenFacetScreenConfigurer(UserInfoScreen userInfoScreen) {
        Post selected = postsTable.getSingleSelected();
        if (selected == null || selected.getUserId() == null) {
            throw new IllegalStateException("No post selected");
        }

        userInfoScreen.setUserId(selected.getUserId());
    }

    /*@Subscribe("postsTable.viewUserInfo")
    public void onPostsTableViewUserInfo(Action.ActionPerformedEvent event) {
        Post selected = postsTable.getSingleSelected();
        if (selected == null || selected.getUserId() == null) {
            return;
        }

        UserInfoScreen userInfoScreen = screenBuilders.screen(this)
                .withScreenClass(UserInfoScreen.class)
                .build();
        userInfoScreen.setUserId(selected.getUserId());

        userInfoScreen.show();
    }*/
}