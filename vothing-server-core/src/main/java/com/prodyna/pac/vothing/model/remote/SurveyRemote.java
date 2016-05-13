package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.model.impl.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyRemote extends Survey implements BaseRemoteEntity {

    private List<String> usersPermissions = new ArrayList<String>();

    @Override
    public List<String> getUsersPermissions() {
        return usersPermissions;
    }

    @Override
    public void setUsersPermissions(List<String> usersPermissions) {
        this.usersPermissions = usersPermissions;
    }
}
