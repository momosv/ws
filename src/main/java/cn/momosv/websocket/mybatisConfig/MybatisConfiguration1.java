package cn.momosv.websocket.mybatisConfig;


import cn.momosv.websocket.dataSource.SqlPrintInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * mybatis的相关配置设置
 * @author Jfei
 *
 */

@MapperScan(value = "cn.momosv.websocket.dao1")
@Configuration
@ConfigurationProperties
@EnableTransactionManagement
public class MybatisConfiguration1 {

	private static Log logger = LogFactory.getLog(MybatisConfiguration1.class);



    //  配置类型别名
    @Value("${mybatis1.type-aliases-package}")
    private String typeAliasesPackage1;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis1.mapper-locations}")
    private String mapperLocations1;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //  加载全局的配置文件
    @Value("${mybatis.config-location}")
    private String configLocation;


    //事务管理
    @Bean(name = "DataSourceTransactionManager1")
    public  PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("dataSource") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }

    @Bean(name = "master1TransactionManager")
    public DataSourceTransactionManager master2TransactionManager(@Qualifier("dataSource") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }

    @Bean(name = "master1SqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource ) throws Exception {
       try{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 读取配置
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage1);
       //设置mybatis-config.xml配置文件位置
       sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        //设置mapper.xml文件所在位置
       //Resource[] resource = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
       Resource[] resource1 = new PathMatchingResourcePatternResolver().getResources(mapperLocations1);
	   List<Resource> rL= new ArrayList<>();
	  // rL.addAll(Arrays.asList(resource));
	   rL.addAll(Arrays.asList(resource1));
	   sessionFactory.setMapperLocations(rL.toArray(new Resource[rL.size()]));

        return sessionFactory.getObject();
    } catch (IOException e) {
        logger.error("mybatis resolver mapper*xml is error",e);
        return null;
    } catch (Exception e) {
        logger.error("mybatis sqlSessionFactoryBean create error",e);
        return null;
    }
    }

}
