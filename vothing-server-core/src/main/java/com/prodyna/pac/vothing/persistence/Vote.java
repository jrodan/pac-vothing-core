package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_vote")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_VOTES, query = "SELECT a FROM Vote a") })
public class Vote implements Serializable {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 1219246602331216625L;
	
	@Id
	@GeneratedValue
	private long voteId;
	
	@Column
	private double rating;
	
	@JoinColumn(name = "userId")
	@ManyToOne User user;
	
	@JoinColumn(name = "surveyId")
	@ManyToOne Survey survey;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getVoteId() {
		return voteId;
	}

	public void setVoteId(long voteId) {
		this.voteId = voteId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * Default constructor.
	 */
	public Vote() {
	}
	
	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vote)) {
			return false;
		}
		final Vote other = (Vote) obj;
		if (this.voteId == 0) {
			if (other.voteId != 0) {
				return false;
			}
		} else if (this.voteId == other.voteId) {
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
