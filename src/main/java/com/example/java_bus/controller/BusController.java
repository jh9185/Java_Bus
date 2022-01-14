package com.example.java_bus.controller;

import com.example.java_bus.bus.BusNumber;
import com.example.java_bus.bus.BusStation;
import com.example.java_bus.service.BusService;
import com.example.java_bus.vo.BusNumberVo;
import com.example.java_bus.vo.BusStationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BusController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BusService busService;


    @GetMapping("/busNumberAlldelete")
    @Transactional
    public String AllDeleteBusNumber() throws IOException {
        busService.deleteAll();
        busService.resetId();
        return "redirect:/";
    }

    @GetMapping("/busNumberFileUpload")
    public String UploadBusNumber() throws IOException {
        List<BusNumberVo> savebusNumberVoList = new ArrayList<BusNumberVo>();
        savebusNumberVoList = busService.findAll();

        if(savebusNumberVoList.size() != 0) {
            busService.deleteAll();
            busService.resetId();
        }
        else{
            savebusNumberVoList.clear();
        }

        BusNumber busNumber = new BusNumber();
        savebusNumberVoList = busNumber.readBusNumber();
        Optional<BusNumberVo> findbusNumberVo;

        if(savebusNumberVoList.size() != 0) {
            busService.saveAll(savebusNumberVoList);
        }

//        for(int i =0; i<savebusNumberVoList.size(); i++){
//            findbusNumberVo = busService.findByrouteId(savebusNumberVoList.get(i).getRouteId());
//            if(findbusNumberVo.isEmpty()){
//                busService.save(savebusNumberVoList.get(i));
//            }
//            else {
//                busService.updateByrouteId(findbusNumberVo.get().getRouteId(), savebusNumberVoList.get(i));
//            }
//        }

        return "redirect:/";
    }

    @GetMapping("/busNumber/{busName}")
    public String searchBusNumber(@PathVariable("busName") String busName) {
        busService.findByName(busName);
        return "redirect:../busstation/stationview";
    }

    @RequestMapping("/busstation/stationview/{busName}")
    public String viewMap(Model model, @PathVariable("busName") String busName) throws IOException {
        Optional<BusNumberVo> busNumberVo = busService.findByName(busName);
        BusStation busStation = new BusStation();
        List<Point2D> busStationPathList = new ArrayList<Point2D>();
        List<BusStationVo> busStationVoList = new ArrayList<BusStationVo>();
        busStationVoList = busStation.busStationLoadData(busNumberVo.get().getRouteId());
        busStationVoList = busStation.BusStationLoadArriveData(busStationVoList);

        busStationPathList = busStation.BusStationLoadPathData(busNumberVo.get().getRouteId());

        model.addAttribute("busStationVoList", busStationVoList);
        model.addAttribute("busStationPathList", busStationPathList);

        return "/busstation/stationview";
    }

}
