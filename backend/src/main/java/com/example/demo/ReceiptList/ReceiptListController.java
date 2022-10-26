package com.example.demo.ReceiptList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receiptlist")
public class ReceiptListController {
	private final ReceipListtImplement receiptImplement;

	
	@Autowired
	public ReceiptListController(ReceipListtImplement receiptImplement) {
		this.receiptImplement = receiptImplement;
	}
	
	@GetMapping("/")
	public Object getAllReceipt() {
		return this.receiptImplement.getAllReceiptList();
	}
	
	@GetMapping("/{rid}")
	public Object getAllRecaiptById(@PathVariable(name = "rid") Long rid) {
		return this.receiptImplement.getAllReceiptListById(rid).getBody();
	}
	
	@PostMapping("/add")
    public Object addNewReceipt(@RequestBody ReceiptList receipt)
    {
        return this.receiptImplement.addNewReceiptList(receipt).getBody();
    }
	
	@PutMapping("/update/{rid}")
	public Object updateReceipt(@RequestBody ReceiptList receipt, @PathVariable (name = "rid") Long rid)
	{
		return this.receiptImplement.UpdateReceiptList(receipt, rid).getBody();
	}
	
}
