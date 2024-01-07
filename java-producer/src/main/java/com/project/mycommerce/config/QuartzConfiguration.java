package com.project.mycommerce.config;

import com.project.mycommerce.service.job.SendUpdatesJob;
import lombok.RequiredArgsConstructor;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class QuartzConfiguration {

  private final DataSource dataSource;
  private final ApplicationContext applicationContext;

  @Value("${jobs.send-updates.cron-expression}")
  private String sendUpdatesCronExpression;

  @Bean
  public CronTrigger sendUpdatesTrigger() {
    CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
    factoryBean.setJobDetail(sendUpdatesJobDetail());
    factoryBean.setName("sendUpdatesTrigger");
    factoryBean.setCronExpression(sendUpdatesCronExpression);
    factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
    try {
      factoryBean.afterPropertiesSet();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return factoryBean.getObject();
  }

  @Bean
  public JobDetail sendUpdatesJobDetail() {
    JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
    factoryBean.setJobClass(SendUpdatesJob.class);
    // job has to be durable to be stored in DB:
    factoryBean.setDurability(true);
    factoryBean.setApplicationContext(applicationContext);
    factoryBean.setName("sendUpdatesJob");
    factoryBean.afterPropertiesSet();
    return factoryBean.getObject();
  }

  @Bean
  public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
    AutowiringSpringBeanFactory autowiringSpringBeanFactory = new AutowiringSpringBeanFactory();
    autowiringSpringBeanFactory.setApplicationContext(applicationContext);

    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setOverwriteExistingJobs(true);
    factory.setAutoStartup(true);
    factory.setDataSource(dataSource);
    factory.setJobFactory(autowiringSpringBeanFactory);
    factory.setQuartzProperties(quartzProperties());
    factory.setWaitForJobsToCompleteOnShutdown(true);
    factory.setTriggers(sendUpdatesTrigger());

    return factory;
  }

  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }
}
