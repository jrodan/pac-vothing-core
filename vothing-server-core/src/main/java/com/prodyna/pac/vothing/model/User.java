package com.prodyna.pac.vothing.model;

import java.util.Collection;
import java.util.Date;

/**
 * Created by jrodan on 13/05/16.
 */
public interface User extends BaseModel<User>{

    String getForeName();

    void setForeName(String foreName);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    Date getLastLogin();

    void setLastLogin(Date lastLogin);

    Collection<Survey> getSurveys();

    void setSurveys(Collection<Survey> surveys);

    Collection<Role> getRoles();

    void setRoles(Collection<Role> roles);

    Collection<SurveyOptionRating> getSurveyOptionRatings();

    void setSurveyOptionRatings(Collection<SurveyOptionRating> votes);

    String toFrontendUserJSONObjectString();
}
