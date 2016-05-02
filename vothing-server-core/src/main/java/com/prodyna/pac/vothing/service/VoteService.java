package com.prodyna.pac.vothing.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.prodyna.pac.vothing.monitoring.VothingMonitoring;
import com.prodyna.pac.vothing.persistence.SurveyOptionRating;

@Path("/restricted/vote")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@VothingMonitoring
public interface VoteService {

//	@GET
//	@Path("/add/{rating}")
//	SurveyOptionRating createVote(@PathParam("rating") double rating);

//	SurveyOptionRating createVote(SurveyOptionRating vote);
//
//	@GET
//	@Path("/list/{voteId}")
//	SurveyOptionRating readVote(@PathParam("voteId") long voteId)
//			throws EntityNotFoundException;
//
//	@GET
//	@Path("/list")
//	List<SurveyOptionRating> readVotes();
//
//	void deleteVote(String name);
//
//	SurveyOptionRating updateVote(SurveyOptionRating vote);

}
