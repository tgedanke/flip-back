package com.vbsoft.Controller;

import com.vbsoft.Modeles.In.PKFInfo;
import com.vbsoft.Services.SamsungDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/apis/")
public class SamsungController {

    @Autowired
    private SamsungDeliveryService service;

    @PostMapping(
            path = "create",
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE)
    public String getMessage(@RequestBody PKFInfo model) {
        try {
            this.service.saveDeliveryToFile(model);
        } catch (IOException e) {
            return this.service.sendErrorMessage(model, e.getMessage());
        }
        return this.service.sendSuccessMessage(model);
    }
}
