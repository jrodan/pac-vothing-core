package com.prodyna.pac.vothing.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_vote")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_VOTES, query = "SELECT a FROM Vote a") })
public class Vote extends BaseModelImpl<Vote> {
	
	/** Generated serial version UID. */
	private static final long serialVersionUID = 1219246602331216625L;
	
	@Column
	private double rating;
	
//	@JoinColumn(name = "id")
	@JoinColumn(name = "userId", referencedColumnName = "id")
	@ManyToOne User user;
	
//	@JoinColumn(name = "id")
	@JoinColumn(name = "surveyId", referencedColumnName = "id")
	@ManyToOne Survey survey;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
}
