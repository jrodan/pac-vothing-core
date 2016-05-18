package com.prodyna.pac.vothing.model.remote;

import com.prodyna.pac.vothing.model.impl.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jrodan on 13/05/16.
 */
public class SurveyRemote extends Survey implements BaseRemoteEntity {

    private List<String> usersPermissions = new ArrayList<String>();

    private boolean isNew = false;

    @Override
    public List<String> getUsersPermissions() {
        return usersPermissions;
    }

    @Override
    public void setUsersPermissions(List<String> usersPermissions) {
        this.usersPermissions = usersPermissions;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }
}
