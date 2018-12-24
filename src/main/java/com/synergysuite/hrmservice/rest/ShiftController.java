package com.synergysuite.hrmservice.rest;

import com.synergysuite.hrmservice.model.Shift;
import com.synergysuite.hrmservice.service.ShiftService;
import com.synergysuite.hrmservice.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shifts")
public class ShiftController {


    @Autowired
    ShiftService shiftService;

    @GetMapping("/")
    public ResponseEntity getAllShifts(){
        try{
            return ResponseEntity.ok(shiftService.getAllShifts());
        }catch (ServiceException e){
            return e.toResponse();
        }
    }

    @PostMapping("/save/{end}/{start}")
    public ResponseEntity saveShift(@PathVariable ("end") java.sql.Time end,@PathVariable("start") java.sql.Time start) throws ServiceException{
        Shift s = new Shift(start,end);
        try {
            return ResponseEntity.ok(shiftService.createShift(s));
        }catch (ServiceException e){
            return e.toResponse();
        }
    }

    @PutMapping("/update/{id}/{end}/{start}")
    public  ResponseEntity updateShift(@PathVariable("id") Long id, @PathVariable ("end") java.sql.Time end,@PathVariable("start") java.sql.Time start) throws ServiceException{

        Shift s = new Shift(id,start,end);
        try{
            return  ResponseEntity.ok(shiftService.updateShift(s));
        }catch (ServiceException e){
            return e.toResponse();
        }

    }
}
