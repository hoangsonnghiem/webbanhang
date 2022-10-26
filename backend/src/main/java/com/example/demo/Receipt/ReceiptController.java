package com.example.demo.Receipt;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.CustomResponse;



@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	private final ReceiptImplement receiptImplement;

	
	@Autowired
	public ReceiptController(ReceiptImplement receiptImplement) {
		this.receiptImplement = receiptImplement;
	}
	
	@GetMapping("/")
	public Object getAllReceipt() {
		return this.receiptImplement.getAllReceipt();
	}
	
	@GetMapping("/temp/{path}")
	public CustomResponse tmpGetReceipt(@PathVariable(name = "path")String path)
	{
		if(path.matches("[a-zA-z]"))
			return this.receiptImplement.getReceipt();
		else return new CustomResponse(400, "Bad Request", "input data type not match", null);
	}
	
	
	
	@GetMapping("/{rid}")
	public Object getAllRecaiptById(@PathVariable(name = "rid") Long rid) {
		
		return this.receiptImplement.getAllReceiptById(rid).getBody();
	}
	
	@PostMapping("/add")
    public Object addNewReceipt(@RequestBody Receipt receipt)
    {
        return this.receiptImplement.addNewReceipt(receipt).getBody();
    }
	
	@PutMapping("/update/{rid}")
	public Object updateReceipt(@RequestBody Receipt receipt, @PathVariable (name = "rid") Long rid)
	{
		return this.receiptImplement.UpdateReceipt(receipt, rid).getBody();
	}
	
	
}
