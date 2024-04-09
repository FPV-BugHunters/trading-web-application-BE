package com.umb.trading_software.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource()
public interface AppUserRoleRepository extends CrudRepository<AppUserRole, Long> {
	Optional<AppUserRole> findByName(String name);
}






