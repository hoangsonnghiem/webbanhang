package com.example.demo.Store;

import com.example.demo.CustomResponse;
import com.example.demo.FormRegex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public Object getAllStore()
    {
        return this.storeService.getAllStoreList().getBody();
    }

    @GetMapping("/{storeid}")
    public Object getStoreById(@PathVariable(name = "storeid") Long storeid)
    {
        return this.storeService.getStoreById(storeid);
    }

    @GetMapping("/active")
    public Object getActiveStore()
    {
        return this.storeService.getActiveStore().getBody();
    }

    @GetMapping("/tmp/active")
    public CustomResponse customActiveStore(HttpServletRequest request)
    {
        CustomResponse response = new CustomResponse();
        JSONObject data = (JSONObject) request.getParameterMap();
        if(!data.get("storeid").toString().matches(String.valueOf(FormRegex.NumberRegex)) ||
            !data.get("email").toString().matches(String.valueOf(FormRegex.EmailRegex)))
            response = new CustomResponse().customNumberFormatException();
        //else return service activestore function value
        return response;
    }

    @PostMapping("/add")
    public Object addStore(@RequestBody Store store)
    {
        return this.storeService.addStore(store).getBody();
    }

    @PutMapping("/update/{storeid}")
    public Object updateStore(@RequestBody Store store, @PathVariable(name = "storeid") Long storeid)
    {
        return this.storeService.updateStore(store, storeid).getBody();
    }

    @PutMapping("delete/{storeid}")
    public Object deleteStore(@PathVariable Long storeid)
    {
        return this.storeService.deleteStore(storeid).getBody();
    }
}
