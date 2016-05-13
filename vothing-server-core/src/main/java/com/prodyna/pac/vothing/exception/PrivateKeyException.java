package com.prodyna.pac.vothing.exception;

import org.slf4j.Logger;

import javax.inject.Inject;

/**
 * Created by jrodan on 13/05/16.
 */
public class PrivateKeyException extends Exception {

    @Inject
    private Logger logger;

    public PrivateKeyException(String message) {
        super(message);
    }
}
