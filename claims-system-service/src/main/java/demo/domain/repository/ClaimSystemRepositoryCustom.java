package demo.domain.repository;

import demo.domain.ClaimSystem;

import java.util.List;

public interface ClaimSystemRepositoryCustom {

    List<ClaimSystem> findByNameContains(String claimName);

}
