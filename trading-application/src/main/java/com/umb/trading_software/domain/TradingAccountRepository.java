package com.umb.trading_software.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


// @RepositoryRestResource(path="trading-account")
public interface TradingAccountRepository extends CrudRepository<AppUser, Long> {

}
