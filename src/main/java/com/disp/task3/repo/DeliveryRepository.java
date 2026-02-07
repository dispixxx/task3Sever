package com.disp.task3.repo;

import com.disp.task3.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    boolean existsByDeliveryNumber(String deliveryNumber);

    @Query("SELECT d FROM Delivery d WHERE d.deliveryDateTime BETWEEN ?1 AND ?2 ORDER BY d.deliveryDateTime DESC")
    List<Delivery> findByDateBetween(LocalDate startDate, LocalDate endDate);
}