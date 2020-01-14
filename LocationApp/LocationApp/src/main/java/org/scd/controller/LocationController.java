package org.scd.controller;

import org.scd.config.exception.BusinessException;
import org.scd.model.Location;
import org.scd.model.User;
import org.scd.model.dto.LocationUpdateDTO;
import org.scd.model.dto.UserRegisterDTO;
import org.scd.model.security.CustomUserDetails;
import org.scd.service.LocationService.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController()


@RequestMapping("/locations")
public class LocationController {
    @Autowired
    LocationService locationService;


    @PostMapping()
    public ResponseEntity<Location> createLocation(@RequestBody final Location location) throws BusinessException {


        final CustomUserDetails userPrincipal= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(locationService.createLocation(userPrincipal,location));

    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Location>getLocationById(@PathVariable final Long id) throws BusinessException{
        final CustomUserDetails userPrincipal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(locationService.findLocationById(userPrincipal,id));

    }
    @PutMapping("/updateById/{id}")
    public ResponseEntity<Location> updateLocationById(@PathVariable("id") final Long locationId, @RequestBody final LocationUpdateDTO updatedInfo) throws BusinessException {
        final CustomUserDetails userPrincipal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(locationService.updateById(userPrincipal, locationId, updatedInfo));
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteLocationById(@PathVariable final Long id) throws BusinessException {
        final CustomUserDetails userPrincipal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        locationService.deleteById(userPrincipal,id);

    }

    @GetMapping("/interval/{userId}")
    public ResponseEntity<List<Location>> getLocationByDateInterval(@PathVariable final Long userId, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam final Date startDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam final    Date endDate) throws BusinessException {
        return ResponseEntity.ok ( locationService.findByDate (userId,startDate,endDate));}
   }


