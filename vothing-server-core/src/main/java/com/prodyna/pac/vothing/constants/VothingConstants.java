package com.prodyna.pac.vothing.constants;

public interface VothingConstants {

    String ORDER_DEFAULT = "createDate DESC";

    String ORDER_MODIFIED_ASC = "modifiedDate ASC";

    String ORDER_MODIFIED_DESC = "modifiedDate DESC"; // TODO

    String ORDER_CREATED_ASC = "createDate ASC";

    String ORDER_CREATED_DESC = "createDate DESC"; // TODO

    String VOTHING_ACCESS_TOKEN = "Vothing-Token";

    int HTTP_CLIENT_STATUS_LOGIN_INVALID = 401;

    int HTTP_SERVER_STATUS_INTERNAL_ERROR = 500;

    int HTTP_STATUS_OK = 200;

    String SERVER_PRIVATE_AUTH_KEY = "vothing.server.private.key";

}
