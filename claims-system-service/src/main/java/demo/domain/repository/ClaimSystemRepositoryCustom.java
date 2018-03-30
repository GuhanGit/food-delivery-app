package demo.domain.repository;

import demo.domain.ClaimSystem;
import demo.domain.OrderStatusUpdateMessage;

import java.util.List;

public interface ClaimSystemRepositoryCustom {

    List<ClaimSystem> findByNameContains(String claimName);
    
    String updateStatus(String claimId, OrderStatusUpdateMessage orderStatusUpdateMessage);

}
