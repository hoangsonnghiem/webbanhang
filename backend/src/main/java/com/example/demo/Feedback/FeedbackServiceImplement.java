package com.example.demo.Feedback;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import com.example.demo.CustomResponse;
import com.example.demo.Receipt.Receipt;

@Service
public class FeedbackServiceImplement implements FeedbackService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseEntity<Object> getAllFeedback() {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
		try {
			String sql = "select * from feedback";
			List<Feedback> feedbacks = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Feedback.class));
			if (feedbacks.size() != 0)
				responseEntity = new ResponseEntity<>(feedbacks, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseEntity;
	}
	@Override
	public ResponseEntity<Object> getFeedbackById(int fid) {
		ResponseEntity<Object> responseEntity = null;
		try {
			String sql = "select * from feedback where fid = ?";
			List<Feedback> feedback = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Feedback.class),
					fid);
			if (feedback.size() != 0)
				responseEntity = new ResponseEntity<>(feedback, HttpStatus.OK);
			else
				responseEntity = new ResponseEntity<>("feedback not found", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			ex.printStackTrace();
			responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@Override
	public ResponseEntity<Object> getFeedbackByPid(int pid) {
		ResponseEntity<Object> responseEntity = null;
		try {
			String sql = "select * from feedback where pid = ?";
			List<Feedback> feedback = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Feedback.class),
					pid);
			if (feedback.size() != 0)
				responseEntity = new ResponseEntity<>(feedback, HttpStatus.OK);
			else
				responseEntity = new ResponseEntity<>("feedback not found", HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			ex.printStackTrace();
			responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@Override
	public ResponseEntity<Object> addNewFeedback(Feedback feedback) {
		ResponseEntity<Object> responseEntity = null;
		HttpStatus status = getFeedbackById(feedback.getFid()).getStatusCode();
		if (status == HttpStatus.OK)
			responseEntity = new ResponseEntity<>("feedback id already existed", HttpStatus.CONFLICT);
		if (status == HttpStatus.NOT_FOUND) {
			try {
				String sql = "insert into feedback values (?, ?, ?, ?, ?, ?, ?)";
				int res = this.jdbcTemplate.update(sql, feedback.toArray());
				if (res == 1) {
					responseEntity = new ResponseEntity<>("completed", HttpStatus.OK);
				} else
					responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
			} catch (Exception ex) {
				ex.printStackTrace();
				responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
			}
		}
		return responseEntity;
	}
	@Override
	public ResponseEntity<Object> updateFeedback(Feedback feedback, int fid) {
		ResponseEntity<Object> responseEntity = null;
		if (feedback.getFid() != fid) {
			responseEntity = new ResponseEntity<>("you cant update feedback id", HttpStatus.CONFLICT);
		} else if (getFeedbackById(fid).getStatusCode().isError()) {
			responseEntity = new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
		} else {
			try {
				String sql = "update feedback set email = ?, pid=?, rating=?, feedback=?, medialink=?, fdate=?  where fid = ?";
				int res = this.jdbcTemplate.update(sql, feedback.toArrayForUpdate());
				if (res == 1) {
					responseEntity = new ResponseEntity<>("update completed", HttpStatus.OK);
				} else
					responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
			} catch (Exception ex) {
				ex.printStackTrace();
				responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
			}
		}
		return responseEntity;
	}
	@Override
	public ResponseEntity<Object> deleteFeedback(Feedback feedback, int fid) {
		ResponseEntity<Object> responseEntity = null;
		if (feedback.getFid() != fid) {
			responseEntity = new ResponseEntity<>("you cant delete feedback id", HttpStatus.CONFLICT);
		} else if (getFeedbackById(fid).getStatusCode().isError()) {
			responseEntity = new ResponseEntity<>("category not found", HttpStatus.NOT_FOUND);
		} else {
			try {
				String sql = "delete feedback set email = ?, pid=?, rating=?, feedback=?, medialink=?, fdate=?  where fid = ?";
				int res = this.jdbcTemplate.update(sql, feedback.toArrayForDelete());
				if (res == 1) {
					responseEntity = new ResponseEntity<>("delete completed", HttpStatus.OK);
				} else
					responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
			} catch (Exception ex) {
				ex.printStackTrace();
				responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
			}
		}
		return responseEntity;
	}
	@Override
	public CustomResponse getFeedback() {
		CustomResponse response = new CustomResponse();
		try
		{
			String sql = "select * from feedback";
			List<Receipt> receipts = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Receipt.class));
			if(receipts.size() == 0)
			{
				response.setCode(204);
				response.setStatus("Empty");
				response.setMessage("empty");
				response.setData(null);
			}
			else 
			{
				response = response.requestCompleted(receipts);
			}
		}
		catch(Exception ex)
		{
			if(ex instanceof ServerErrorException)
			{
				response = response.serverErrorException();
			}
			else if(ex instanceof NotFoundException)
			{
				response = response.notFoundException();
			}
		}
		
		return response;
	}
}
