package com.vbsoft.Controller;

import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Services.SamsungDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class SamsungController {

    @Autowired
    private SamsungDeliveryService service;

    @PostMapping(
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public String getMessage(@RequestBody PKFInfo model) {
        try {
            this.service.saveDeliveryToFile(model);
            this.service.saveSamsungRequest(model);
        } catch (IOException e) {
            return this.service.sendErrorMessage(model, e.getMessage());
        }
        return this.service.sendSuccessMessage(model);
    }
}
