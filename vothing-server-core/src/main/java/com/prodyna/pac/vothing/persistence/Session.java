package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

/*
 * 
 * TODO 
 * cleanup session logic
 * 
 */

@Entity
@XmlRootElement
@Table(name = "vothing_session")
@NamedQueries({
		@NamedQuery(name = VothingConstants.SELECT_ALL_SESSIONS, query = "SELECT a FROM Session a"),
		@NamedQuery(name = VothingConstants.SELECT_SESSION_BY_TOKEN, query = "SELECT a FROM Session a WHERE a.sessionKey = :sessionKey") })
public class Session implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7606525115208075441L;

	@Id
	@GeneratedValue
	private long sessionId;

	// @Temporal(TemporalType.DATE)
	@Column
	private Date expirationDate;

	@Column
	private String sessionKey;

	@Column
	private String ip;

	@OneToOne
	@JoinColumn(name = "userId")
	User user;

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Default constructor.
	 */
	public Session() {
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Session)) {
			return false;
		}
		final Session other = (Session) obj;
		if (this.sessionId == 0) {
			if (other.sessionId != 0) {
				return false;
			}
		} else if (this.sessionId == other.sessionId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
