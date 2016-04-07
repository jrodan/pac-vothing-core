package com.prodyna.pac.vothing.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Session;
import com.prodyna.pac.vothing.persistence.User;
import com.prodyna.pac.vothing.service.SessionService;

@Stateless
@VothingMonitoring
public class SessionServiceImpl implements SessionService {

	@Inject
	private Vothing vothing;
	
	@Inject
	private Logger logger;

	@Override
	public Session getSession(String sessionKey) {
		Session session = null;
		
		List<Session> sessions = vothing
				.getEntityManager()
				.createNamedQuery(VothingConstants.SELECT_SESSION_BY_TOKEN,
						Session.class).getResultList();

		if (sessions != null) {
			if (sessions.size() > 1) {
				logger.warn("more than one token keys for the given token were found - will return the first one: "
						+ sessionKey);
			}
			session = sessions.get(0);
		}
		
		return session;
	}

	@Override
	public Session createSession(User user, Date date) {
		Session session = new Session();
		session.setExpirationDate(date);
		session.setSessionKey(getRandomKey());
		session.setUser(user);
//		user.setSession(session);
		vothing.getEntityManager().persist(session);
		return session;
	}

	@Override
	public Session createSession(User user, int calendarUnit, int calendarValue) {
		Calendar cal = Calendar.getInstance();
		cal.add(calendarUnit, calendarValue);
		return createSession(user, cal.getTime());
	}

	private String getRandomKey() {
		char[] random = new char[30];
		for (int j = 0; j < random.length; j++) {
			random[j] = (char) ('a' + 25 * Math.random());
		}
		return String.valueOf(random);
	}

}
