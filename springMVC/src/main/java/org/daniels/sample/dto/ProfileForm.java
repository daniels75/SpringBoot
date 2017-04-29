package org.daniels.sample.dto;

import com.google.common.collect.Lists;
import org.daniels.sample.validator.PastLocalDate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by daniels on 26.04.2017.
 */
public class ProfileForm {

    @Size(min = 2)
    private String twitterHandle;

    @Email
    @NotEmpty
    private String email;

    @NotNull
    @PastLocalDate
    private LocalDate birthDate;

    @NotEmpty
    private List<String> tastes = Lists.newArrayList();

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getTastes() {
        return tastes;
    }

    public void setTastes(List<String> tastes) {
        this.tastes = tastes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileForm that = (ProfileForm) o;

        if (twitterHandle != null ? !twitterHandle.equals(that.twitterHandle) : that.twitterHandle != null)
            return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        return tastes != null ? tastes.equals(that.tastes) : that.tastes == null;
    }

    @Override
    public int hashCode() {
        int result = twitterHandle != null ? twitterHandle.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (tastes != null ? tastes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProfileForm{" +
                "twitterHandle='" + twitterHandle + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", tastes=" + tastes +
                '}';
    }
}
