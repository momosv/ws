package cn.momosv.websocket.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class Datasource2Config {
	private Logger logger = LoggerFactory.getLogger(Datasource2Config.class);
	
    @Value("${spring.datasource2.url}")
    private String dbUrl;
    
    @Value("${spring.datasource2.type}")
    private String dbType;
    
    @Value("${spring.datasource2.username}")
    private String username;

    @Value("${spring.datasource2.name}")
    private String name;
    
    @Value("${spring.datasource2.password}")
    private String password;
    
    @Value("${spring.datasource2.driver-class-name}")
    private String driverClassName;
    
    @Value("${spring.datasource2.initialSize}")
    private int initialSize;
    
    @Value("${spring.datasource2.minIdle}")
    private int minIdle;
    
    @Value("${spring.datasource2.maxActive}")
    private int maxActive;
    
    @Value("${spring.datasource2.maxWait}")
    private int maxWait;
    
    @Value("${spring.datasource2.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    
    @Value("${spring.datasource2.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    
    @Value("${spring.datasource2.validationQuery}")
    private String validationQuery;
    
    @Value("${spring.datasource2.testWhileIdle}")
    private boolean testWhileIdle;
    
    @Value("${spring.datasource2.testOnBorrow}")
    private boolean testOnBorrow;
    
    @Value("${spring.datasource2.testOnReturn}")
    private boolean testOnReturn;
    
    @Value("${spring.datasource2.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.filters}")
    private String filters;

    
	
    @Bean(name="dataSource2")
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();  
        try {  
	        datasource.setUrl(this.dbUrl);  
	        datasource.setDbType(dbType);
	        datasource.setUsername(username);  
	        datasource.setPassword(password);  
	        datasource.setDriverClassName(driverClassName);  
	        datasource.setInitialSize(initialSize);  
	        datasource.setMinIdle(minIdle);  
	        datasource.setMaxActive(maxActive);  
	        datasource.setMaxWait(maxWait);  
	        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
	        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
	        datasource.setValidationQuery(validationQuery);  
	        datasource.setTestWhileIdle(testWhileIdle);  
	        datasource.setTestOnBorrow(testOnBorrow);  
	        datasource.setTestOnReturn(testOnReturn);  
	        datasource.setPoolPreparedStatements(poolPreparedStatements);  
            datasource.setFilters(filters);  
        } catch (SQLException e) {  
            logger.error("druid configuration initialization filter", e);  
        }  
        return datasource;  
    }

}  

