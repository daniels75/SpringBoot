package org.daniels.sample.profile;

import com.google.common.collect.Lists;
import org.daniels.sample.dto.ProfileForm;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by daniels on 06.07.2017.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserProfileSession implements Serializable {

    private String twitterHandle;
    private String email;
    private LocalDate birithDate;
    private List<String> tastes = Lists.newArrayList();

    public void saveForm(ProfileForm profileForm) {
        this.twitterHandle = profileForm.getTwitterHandle();
        this.email = profileForm.getEmail();
        this.birithDate = profileForm.getBirthDate();
        this.tastes = profileForm.getTastes();
    }

    public ProfileForm toForm() {
        ProfileForm profileForm = new ProfileForm();
        profileForm.setTwitterHandle(twitterHandle);
        profileForm.setEmail(email);
        profileForm.setBirthDate(birithDate);
        profileForm.setTastes(tastes);
        return profileForm;
    }
}
