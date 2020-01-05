package org.scd.repository;

import org.scd.model.Location;
import org.scd.model.security.CustomUserDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LocationRepository  extends CrudRepository<Location,Long> {

    @Override
    void deleteById(Long aLong);


/*
    @Query(value = "SELECT location FROM Location location WHERE location.date <=:endDate AND location.date >=:startDate AND location.user.id =:user_id")
  List<Location> findLocations (Date startDate, Date endDate,  Long userId);
*/
@Query("SELECT l FROM Location l WHERE  l.creationDate >=:startDate AND l.creationDate <=:endDate and l.user.id=:userId")
List<Location> findByDate (Long userId, Date startDate,Date endDate);
}
