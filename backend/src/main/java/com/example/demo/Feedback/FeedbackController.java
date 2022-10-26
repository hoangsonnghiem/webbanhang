package com.example.demo.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Product.ProductServiceImplement;


@RestController
@RequestMapping(path="/feedback")

public class FeedbackController {
	private final FeedbackServiceImplement feedbackService;
	@Autowired
	public FeedbackController(FeedbackServiceImplement feedbackService, ProductServiceImplement productService) {
		this.feedbackService = feedbackService;
	}
	
	@GetMapping("/")
	public Object getAllFeedback() {
		return this.feedbackService.getAllFeedback().getBody();
	}
	
	@GetMapping("/{fid}")
	public Object getFeedbackById(@PathVariable(name ="fid") int fId) {
		return this.feedbackService.getFeedbackById(fId).getBody();
	}
	
	@GetMapping("/get/{pid}")
	public Object getFeedbackByPid(@PathVariable(name="pid") int pid) {
		return this.feedbackService.getFeedbackByPid(pid).getBody();
	}
	
	@PostMapping("/add")
	public Object addFeedback(@RequestBody Feedback feedback) {
		return this.feedbackService.addNewFeedback(feedback).getBody();	
	}
	
	@PutMapping("/update/{fid}")
	public Object updateFeedback(@RequestBody Feedback feedback, @PathVariable(name = "fid") int fid) {
		return this.feedbackService.updateFeedback(feedback, fid).getBody();
	} 
	@DeleteMapping("/delete/{fid}")
	public Object deleteFeedback(@RequestBody Feedback feedback, @PathVariable(name = "fid") int fid) {
	return this.feedbackService.deleteFeedback(feedback, fid).getBody();
	}
}
	
