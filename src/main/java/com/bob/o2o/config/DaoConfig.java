package com.bob.o2o.config;

import java.beans.PropertyVetoException;
import java.io.IOException;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/** 
* @author bob 
* @version 创建时间：2018年8月4日 下午10:27:41 
* 类说明 
*/
@Configuration
@MapperScan("com.bob.o2o.dao")
public class DaoConfig {
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUserName;
	@Value("${jdbc.password}")
	private String jdbcPassword;
	
	@Bean(name="dataSource")
	public DataSource createDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setDriverClass(jdbcDriver);
		dataSource.setUser(jdbcUserName);
		dataSource.setPassword(jdbcPassword);
		
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		dataSource.setAutoCommitOnClose(false);
		dataSource.setCheckoutTimeout(10000);
		dataSource.setAcquireRetryAttempts(2);
		return dataSource;
		
	}
	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(
			DataSource dataSource
			) throws PropertyVetoException, IOException {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
//		factoryBean.setTypeAliasesPackage("com.bob.o2o.entity");
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		factoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
//		factoryBean.setMapperLocations(resolver.getResources("com.bob.o2o.dao.mapper.*.xml"));
		return factoryBean;
	}

}
