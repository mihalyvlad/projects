package org.scd.service.LocationService;

import org.scd.config.exception.BusinessException;
import org.scd.model.Location;
import org.scd.model.dto.LocationUpdateDTO;
import org.scd.model.security.CustomUserDetails;
import org.scd.repository.LocationRepository;
import org.scd.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class LocationServiceImpl implements  LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Location createLocation(CustomUserDetails customUserDetails, Location location) throws BusinessException {
        if (Objects.isNull(location)) {
            throw new BusinessException(401, "Body null!");
        }
        if (Objects.isNull(location.getLatitude())) {
            throw new BusinessException(400, "Latitude can not be null!");
        }
        if (Objects.isNull(location.getLongitude())) {
            throw new BusinessException(400, "Longitude can not be null!");
        }
        Location basicLocation = new Location();
        //LocalDateTime localDateTime=new LocalDateTime();
        basicLocation.setCreationDate(new Date());
        basicLocation.setLatitude(location.getLatitude());
        basicLocation.setLongitude(location.getLongitude());
        basicLocation.setUser(customUserDetails.getUser());
        locationRepository.save(basicLocation);
        return basicLocation;


    }

    @Override
    public Location findLocationById(CustomUserDetails userPrincipal, Long id) throws BusinessException {
        if (Objects.isNull(id)) {
            throw new BusinessException(400, "Id can not be null!");
        }
        Location location = locationRepository.findById(id).orElse(null);
        if (location == null) {
            throw new BusinessException(404, "Location not found");}
        return location;

    }
    @Override
    public Location updateById(CustomUserDetails userPrincipal, Long id, LocationUpdateDTO updatedInfo) throws BusinessException {
        Location updateLocation = findLocationById(userPrincipal, id);
        updateLocation.setLatitude(updatedInfo.getLatitude());
        updateLocation.setLongitude(updatedInfo.getLongitude());
        return updateLocation;
    }
    @Override
    public void deleteById(CustomUserDetails userPrincipal, Long id) throws BusinessException {
        Location location=findLocationById(userPrincipal, id);
        if (location != null) {
            locationRepository.deleteById(id);
        }}


    public List<Location> findByDate(Long userId, Date minDate, Date maxDate) {

        return (List<Location>) locationRepository.findByDate(userId,minDate,maxDate);
    }




    }


