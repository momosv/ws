package cn.momosv.websocket.mybatisConfig;


import cn.momosv.websocket.dataSource.SqlPrintInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
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
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

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

@MapperScan(value = "cn.momosv.websocket.dao2",sqlSessionFactoryRef = "master2SqlSessionFactory")
@Configuration
@ConfigurationProperties
@EnableTransactionManagement
public class MybatisConfiguration2 {

	private static Log logger = LogFactory.getLog(MybatisConfiguration2.class);


    //  配置类型别名
    @Value("${mybatis2.type-aliases-package}")
    private String typeAliasesPackage2;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis2.mapper-locations}")
    private String mapperLocations2;

    //  配置mapper的扫描，找到所有的mapper.xml映射文件
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    //  加载全局的配置文件
    @Value("${mybatis.config-location}")
    private String configLocation;


    //事务管理
    @Bean(name = "DataSourceTransactionManager2")
    public  PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("dataSource2") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }

    @Bean(name = "master2TransactionManager")
    public DataSourceTransactionManager master2TransactionManager(@Qualifier("dataSource2") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }

    @Bean(name = "master2SqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource2 ) throws Exception {
       try{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource2);

        // 读取配置
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage2);
       //设置mybatis-config.xml配置文件位置
       sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));
        //设置mapper.xml文件所在位置
        Resource[] resource = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
        Resource[] resource2 = new PathMatchingResourcePatternResolver().getResources(mapperLocations2);
        List<Resource> rL= new ArrayList<>();
           rL.addAll(Arrays.asList(resource));
           rL.addAll(Arrays.asList(resource2));
        sessionFactory.setMapperLocations(rL.toArray(new Resource[rL.size()]));

        //添加分页插件、打印sql插件
        Interceptor[] plugins = new Interceptor[]{pageHelper(),sqlPrintInterceptor()};
        sessionFactory.setPlugins(plugins);

        return sessionFactory.getObject();
    } catch (IOException e) {
        logger.error("mybatis resolver mapper*xml is error",e);
        return null;
    } catch (Exception e) {
        logger.error("mybatis sqlSessionFactoryBean create error",e);
        return null;
    }
    }


        //将要执行的sql进行日志打印(不想拦截，就把这方法注释掉)

        public SqlPrintInterceptor sqlPrintInterceptor(){
        	return new SqlPrintInterceptor();
        }




        public PageHelper pageHelper() {
            PageHelper pageHelper = new PageHelper();
            Properties p = new Properties();
            p.setProperty("offsetAsPageNum", "true");
            p.setProperty("rowBoundsWithCount", "true");
            p.setProperty("reasonable", "true");
            p.setProperty("returnPageInfo", "check");
            p.setProperty("params", "count=countSql");
            p.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
            p.setProperty("reasonable", "true");//页码<=0 查询第一页，页码>=总页数查询最后一页
            p.setProperty("supportMethodsArguments", "true");//支持通过 Mapper 接口参数来传递分页参数
            pageHelper.setProperties(p);
            return pageHelper;
        }



}
