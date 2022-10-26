package com.example.demo.Receipt;

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

@Service
public class ReceiptImplement implements ReceiptService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ResponseEntity<Object> getAllReceipt() {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>("faild", HttpStatus.BAD_REQUEST);
		try {
			String sql = "select * from receipt";
			List<Receipt> receipts = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Receipt.class));
			if (receipts.size() != 0) {
				responseEntity = new ResponseEntity<>(receipts, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Object> getAllReceiptById(Long rid) {
		ResponseEntity<Object> responseEntity = null;
		try {
			String sql = "select * from receipt where rid=?";
			List<Receipt> receipt = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Receipt.class), rid);
			if (receipt.size() != 0) {
				responseEntity = new ResponseEntity<>(receipt, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>("Receipt not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	@Override
	public ResponseEntity<Object> addNewReceipt(Receipt receipt) {
		ResponseEntity<Object> responseEntity = null;
		HttpStatus status = getAllReceiptById(receipt.getRid()).getStatusCode();
		if (status == HttpStatus.OK)
			responseEntity = new ResponseEntity<>("receipt id already existed", HttpStatus.CONFLICT);
		if (status == HttpStatus.NOT_FOUND) {
			try {
				String sql = "insert into receipt values (?, ?, ?, ?, ?, ?, ?, ?)";
				int res = this.jdbcTemplate.update(sql,
						new Object[] { receipt.getRid(), receipt.getEmail(), receipt.getStid(), receipt.getPayment(),
								receipt.getAddress(), receipt.getNote(), receipt.getRdate(),
								receipt.getReceiptstate() });

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
	public ResponseEntity<Object> UpdateReceipt(Receipt receipt, Long rid) {
		ResponseEntity<Object> responseEntity = null;
		if(rid != receipt.getRid()) {
			responseEntity = new ResponseEntity<>("receipt id cant be updated", HttpStatus.CONFLICT);
		}
		else {
			try {
				int res;
				String sql = "update receipt set email = ?, stid = ?, payment = ?, address = ?, note = ?, rdate = ?, RECEIPTSTATE = ? where rid = ?";
				res = this.jdbcTemplate.update(sql, receipt.toArrayForUpdate());
				if(res == 1) {
					responseEntity = new ResponseEntity<>("update completed", HttpStatus.OK);
				}
				else {
					 responseEntity = new ResponseEntity<>("request failed", HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return responseEntity;
	}

	@Override
	public CustomResponse getReceipt() {
		CustomResponse response = new CustomResponse();
		try
		{
			String sql = "select * from receipt";
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
