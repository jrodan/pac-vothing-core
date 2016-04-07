package com.prodyna.pac.vothing.service;

import java.util.Date;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Session;
import com.prodyna.pac.vothing.persistence.User;

@VothingMonitoring
public interface SessionService {

	Session createSession(User user, Date date);
	
	Session getSession(String sessionKey);

	Session createSession(User user, int calendarUnit, int calendarValue);

}
