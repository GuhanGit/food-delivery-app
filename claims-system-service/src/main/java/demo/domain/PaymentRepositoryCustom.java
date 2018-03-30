package demo.domain;


import java.util.List;

public interface PaymentRepositoryCustom {

	Payment findByClaimsId(String claimId);

}
