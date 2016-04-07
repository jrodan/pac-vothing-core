package com.prodyna.pac.vothing.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.Vote;
import com.prodyna.pac.vothing.service.VoteService;

@Stateless
@VothingMonitoring
public class VoteServiceImpl implements VoteService {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

	@Override
	public Vote createVote(double rating) {

		Vote vote = new Vote();
		vote.setRating(rating);
		vote.setUser(vothing.getUser());
		this.vothing.getEntityManager().persist(vote);

		return vote;
	}

	@Override
	public Vote createVote(Vote vote) {
		this.vothing.getEntityManager().persist(vote);
		return vote;
	}

	@Override
	public List<Vote> readVotes() {
		logger.trace("Retrieving all votes from DB.");

		List<Vote> votes = this.vothing
				.getEntityManager()
				.createNamedQuery(VothingConstants.SELECT_ALL_VOTES, Vote.class)
				.getResultList();

		return votes;
	}

	@Override
	public Vote updateVote(final Vote vote) {
		return this.vothing.getEntityManager().merge(vote);
	}

	@Override
	public void deleteVote(final String name) {
		final Vote vote = this.vothing.getEntityManager()
				.find(Vote.class, name);
		this.vothing.getEntityManager().remove(vote);
	}

	@Override
	public Vote readVote(long voteId) throws EntityNotFoundException {
		final Vote vote = this.vothing.getEntityManager().find(Vote.class,
				voteId);
		if (vote == null) {
			throw new EntityNotFoundException(
					"Vote could not be found for given voteId [" + voteId + "]");
		}

		return vote;
	}

}
