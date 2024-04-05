package com.umb.trading_software.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="ctrader-account")
public interface CtraderAccountRepository extends CrudRepository<AppUser, Long> {


}
