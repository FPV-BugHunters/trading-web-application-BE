package com.umb.trading_software.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="app-users")
public interface AppUserRepository extends CrudRepository<AppUser, Long> {


}
