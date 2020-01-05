package org.scd.service.LocationService;

import org.scd.config.exception.BusinessException;
import org.scd.model.Location;
import org.scd.model.dto.LocationUpdateDTO;
import org.scd.model.security.CustomUserDetails;

import java.util.Date;
import java.util.List;

public interface LocationService{
    Location createLocation(final CustomUserDetails userDetails,final Location location) throws BusinessException;
  Location findLocationById (final CustomUserDetails userPrincipal, final Long id) throws BusinessException;
    Location updateById(CustomUserDetails userPrincipal, final Long id, LocationUpdateDTO updatedInfo) throws BusinessException;

    void deleteById(CustomUserDetails userPrincipal, Long id) throws BusinessException;
    List<Location> findByDate( Long userId, Date minDate, Date maxDate) throws BusinessException;

}
