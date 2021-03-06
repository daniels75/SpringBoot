package org.daniels.sample.utils;

/**
 * Created by daniels on 16.04.2018.
 */
import org.daniels.sample.profile.UserProfileSession;
import org.springframework.mock.web.MockHttpSession;

import java.util.Arrays;

public class SessionBuilder {
    private final MockHttpSession session;
    UserProfileSession sessionBean;

    public SessionBuilder() {
        session = new MockHttpSession();
        sessionBean = new UserProfileSession();
        session.setAttribute("scopedTarget.userProfileSession", sessionBean);
    }

    public SessionBuilder userTastes(String... tastes) {
        sessionBean.setTastes(Arrays.asList(tastes));
        return this;
    }

    public MockHttpSession build() {
        return session;
    }
}