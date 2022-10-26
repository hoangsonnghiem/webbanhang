package com.example.demo.Feedback;

import org.springframework.http.ResponseEntity;

import com.example.demo.CustomResponse;

public interface FeedbackService {
	ResponseEntity<Object>  getAllFeedback();
	ResponseEntity<Object>  getFeedbackById(int fid);
	ResponseEntity<Object>  getFeedbackByPid(int pid);
	ResponseEntity<Object>  addNewFeedback(Feedback feedback);
	ResponseEntity<Object>  updateFeedback(Feedback feedback, int fid );
	ResponseEntity<Object> deleteFeedback(Feedback feedback, int fid );
	CustomResponse getFeedback();
}
