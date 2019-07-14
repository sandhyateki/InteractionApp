package com.sample.interaction.controller;

import com.sample.interaction.Processor.InteractionProcessor;
import com.sample.interaction.model.AppDtlsResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interaction")
public class InteractionController {

    @Autowired
    InteractionProcessor interactionProcessor;

    @RequestMapping(value = "/application/getDetails",  method = RequestMethod.GET)
    public ResponseEntity<AppDtlsResponseTO> getApplicationDetails(@RequestParam(value = "fileName") String fileName,
                                                                    @RequestParam(value = "fileType") String fileType) {

        AppDtlsResponseTO response = interactionProcessor.getResponse(fileName, fileType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
