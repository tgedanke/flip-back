package com.flippost.Controllers;

import com.flippost.DAO.Samsung.NewDelivery.DAO.SamsungNewDeliveryDAO;
import com.flippost.DAO.Samsung.NewDelivery.DAO.SamsungNewDeliveryDAOImpl;
import com.flippost.DAO.Samsung.NewDelivery.Models.SamsungNewDeliveryBase;
import com.flippost.Tools.Statistic.FlippostStatistic;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/Samsung/")
public class SamsungMapController {

    @GetMapping(value = "newDelivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getter() {
        //Do nothing
    }

    @PostMapping(value = "newDelivery",
            produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewDelivery(@RequestBody String model) {

        try {
            SamsungNewDeliveryBase delivery = new SamsungNewDeliveryBase();
            delivery.setNewDeliveryXML(model);
            delivery.xmlToModel();
           /* SamsungNewDeliveryDAO dao = new SamsungNewDeliveryDAOImpl();

            dao.insert();*/
           // delivery.validateXML();
       // } catch (SAXException e) {
         //   FlippostStatistic.addError(e.getMessage(), e);
           // return String.format("{\"state\": false, \"message\":\"%s\" }", e.getMessage());
        } catch (IOException e) {
            FlippostStatistic.addError(e.getMessage(), e);
            return String.format("{\"state\": false, \"message\":\"%s\" }", e.getMessage());
        }
        return "{\"state\": true}";
    }
}
