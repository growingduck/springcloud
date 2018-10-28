package com.imooc.order.repository;

import com.imooc.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by 廖师兄
 * 2017-12-10 16:11
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Optional<OrderMaster> findById(String orderId);
}
