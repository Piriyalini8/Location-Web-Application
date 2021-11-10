package com.priya.location.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.priya.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
