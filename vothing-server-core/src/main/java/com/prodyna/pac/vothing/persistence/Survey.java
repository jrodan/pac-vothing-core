package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_survey")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_SURVEYS, query = "SELECT a FROM Survey a") })
public class Survey implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7912134879739982095L;

	@Id
	@GeneratedValue
	private long surveyId;

	@Column
	private String name;

	@OneToMany(mappedBy = "survey")
	private Collection<Vote> votes;

	@ManyToOne
	User user;

	public long getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(long surveyId) {
		this.surveyId = surveyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Vote> getVotes() {
		return votes;
	}

	public void setVotes(Collection<Vote> votes) {
		this.votes = votes;
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
	public Survey() {
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Survey)) {
			return false;
		}
		final Survey other = (Survey) obj;
		if (this.surveyId == 0) {
			if (other.surveyId != 0) {
				return false;
			}
		} else if (this.surveyId == other.surveyId) {
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
