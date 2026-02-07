package com.disp.task3.repo;


import com.disp.task3.model.DeliveryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long> {

    @Query("""
        SELECT di FROM DeliveryItem di 
        JOIN di.delivery d 
        WHERE d.deliveryDateTime BETWEEN ?1 AND ?2
        """)
    List<DeliveryItem> findByDeliveryDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
