package com.umb.trading_software.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource()
public interface CtraderAccountRepository extends CrudRepository<CtraderAccount, Long> {
    Optional<CtraderAccount> findByAccessToken(String accessToken);
}
