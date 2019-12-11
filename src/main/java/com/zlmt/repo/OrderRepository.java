package com.zlmt.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zlmt.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Order findByOrderId(Long orderId);

	List<Order> getOrderIdByDoneTimeAndCancelTime(LocalDate doneTime, LocalDate cancelTime);

}