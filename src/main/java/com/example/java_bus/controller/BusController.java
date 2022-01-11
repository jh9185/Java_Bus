package com.example.java_bus.controller;

import com.example.java_bus.bus.BusNumber;
import com.example.java_bus.bus.BusStation;
import com.example.java_bus.service.BusService;
import com.example.java_bus.vo.BusNumberVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BusController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BusService busService;

    @GetMapping("/busNumberFileUpload")
    public String UploadBusNumber() throws IOException {
        BusNumber busNumber = new BusNumber();
        List<BusNumberVo> savebusNumberVoList = busNumber.readBusNumber();
        Optional<BusNumberVo> findbusNumberVo;

        for(int i =0; i<savebusNumberVoList.size(); i++){
            findbusNumberVo = busService.findByrouteId(savebusNumberVoList.get(i).getRouteId());
            if(findbusNumberVo.isEmpty()){
                busService.save(savebusNumberVoList.get(i));
            }
            else {
                busService.updateByrouteId(findbusNumberVo.get().getRouteId(), savebusNumberVoList.get(i));
            }
        }

        return "redirect:/";
    }

    @GetMapping("/busNumber/{busName}")
    public String searchBusNumber(@PathVariable("busName") String busName) {
        busService.findByName(busName);
        return "index";
    }
}
