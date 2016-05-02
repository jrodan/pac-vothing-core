package com.prodyna.pac.vothing.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.VothingConstants;
import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.SurveyOptionRating;
import com.prodyna.pac.vothing.service.VoteService;

@Stateless
@VothingMonitoring
public class VoteServiceImpl implements VoteService {

	@Inject
	private Logger logger;

	@Inject
	private Vothing vothing;

//	@Override
//	public SurveyOptionRating createVote(double rating) {
//
//		SurveyOptionRating vote = new SurveyOptionRating();
//		vote.setRating(rating);
//		vote.setUser(vothing.getUser());
//		this.vothing.getEntityManager().persist(vote);
//
//		return vote;
//	}
//
//	@Override
//	public SurveyOptionRating createVote(SurveyOptionRating vote) {
//		this.vothing.getEntityManager().persist(vote);
//		return vote;
//	}
//
//	@Override
//	public List<SurveyOptionRating> readVotes() {
//		logger.trace("Retrieving all votes from DB.");
//
//		List<SurveyOptionRating> votes = this.vothing
//				.getEntityManager()
//				.createNamedQuery(VothingConstants.SELECT_ALL_VOTES, SurveyOptionRating.class)
//				.getResultList();
//
//		return votes;
//	}
//
//	@Override
//	public SurveyOptionRating updateVote(final SurveyOptionRating vote) {
//		return this.vothing.getEntityManager().merge(vote);
//	}
//
//	@Override
//	public void deleteVote(final String name) {
//		final SurveyOptionRating vote = this.vothing.getEntityManager()
//				.find(SurveyOptionRating.class, name);
//		this.vothing.getEntityManager().remove(vote);
//	}
//
//	@Override
//	public SurveyOptionRating readVote(long voteId) throws EntityNotFoundException {
//		final SurveyOptionRating vote = this.vothing.getEntityManager().find(SurveyOptionRating.class,
//				voteId);
//		if (vote == null) {
//			throw new EntityNotFoundException(
//					"Vote could not be found for given voteId [" + voteId + "]");
//		}
//
//		return vote;
//	}

}
