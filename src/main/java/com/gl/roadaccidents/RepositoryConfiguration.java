package com.gl.roadaccidents;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by gavin on 16-5-13.
 */
@Configuration
@EnableJpaRepositories(basePackages={"com.gl.roadaccidents.repository"})
public class RepositoryConfiguration {

}
