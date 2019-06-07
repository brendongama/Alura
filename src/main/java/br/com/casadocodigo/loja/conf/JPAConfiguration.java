package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class JPAConfiguration {

	/**
	 * Faz a parte de Configuração e conexão com o banco de dados
	 * @return 
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityMagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean(); 
		JpaVendorAdapter vendorAdaptor = new HibernateJpaVendorAdapter();		
		factoryBean.setJpaVendorAdapter(vendorAdaptor);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");		
		factoryBean.setDataSource(dataSource);
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(props);
		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");
		
		return factoryBean;
	}
}
