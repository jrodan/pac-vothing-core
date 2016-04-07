package com.prodyna.pac.vothing.persistence;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.prodyna.pac.vothing.constants.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_survey")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_SURVEYS, query = "SELECT a FROM Survey a") })
public class Survey extends BaseModelImpl<Survey> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7912134879739982095L;

	@OneToMany(mappedBy = "survey")
	private Collection<Vote> votes;
	
	@ManyToOne
	User user;

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

}
