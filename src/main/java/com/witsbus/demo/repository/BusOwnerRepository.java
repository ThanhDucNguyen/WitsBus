package com.witsbus.demo.repository;

import com.witsbus.demo.models.BusOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusOwnerRepository extends JpaRepository<BusOwner, Long> {
}
