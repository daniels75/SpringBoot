package org.daniels.sample.authentication;

/**
 * Created by daniels on 10.03.2018.
 */
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * SingInAdapter allows authenticate with Spring Security.
 * Here user is authenticated by Twitter with OAuth1 service.
 * This is a bridge between ProviderSignInController and signIn-provider,
 * it is called in the end from OAuth1 callback when user is finally authentication by
 * external service, here Twitter.
 * SecurityContextHolder is need to store authenticated user in the application context.
 * If user is NOT saved in the UsersConnectionRepository then Sing-Up method is called first.
 */
@Component
public class AuthenticatingSignInAdapter implements SignInAdapter {

    public static void authenticate(Connection<?> connection) {
        UserProfile userProfile = connection.fetchUserProfile();
        String username = userProfile.getUsername();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(String.format("User %s %s connected.", userProfile.getFirstName(), userProfile.getLastName()));
    }

    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        authenticate(connection);
        return null;
    }
}