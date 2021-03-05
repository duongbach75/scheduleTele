package com.savis.categories.controller;


import java.util.Date;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.savis.categories.common.quartz.jobs.SchedulerService;
import com.savis.categories.common.quartz.jobs.SynchronizeCronJob;
@RestController
@Configuration
public class JobScheduleResource {
	@Autowired
	SchedulerService schedulerService;;
	@Autowired
	SynchronizeCronJob cronJob;
	@RequestMapping(value = "/job/all", method = RequestMethod.GET)
    @Bean
    public ResponseEntity<Boolean> scheduleAll() throws SchedulerException {

        String jobGroup = "DONG_BO_HO_SO";

        this.schedulerService.deleteJob("DONG_BO_HO_SO", jobGroup);

        if(!this.schedulerService.isJobWithNamePresent("DONG_BO_HO_SO" , jobGroup)) {

            this.schedulerService.scheduleCronJob("DONG_BO_HO_SO", jobGroup, SynchronizeCronJob.class, new Date(), "0 42 12,17 * * ?");
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
//	
//	@RequestMapping(value = "/job/update", method = RequestMethod.GET)
//    public ResponseEntity<Boolean> updateJob(
//            @RequestParam("jobName") String jobName,
//            @RequestParam("jobGroup") String jobGroup,
//            @RequestParam("cronExpression") String cronExpression
//    ) {
//
//        //Job Name is mandatory
//        if (jobName == null || jobName.trim().equals("")) {
//            return new ResponseEntity<>(false, HttpStatus.OK);
//        }
//
//        //Edit Job
//        if (this.schedulerService.isJobWithNamePresent(jobName, jobGroup)) {
//            SchedulerJobInfo info = this.schedulerRepository.findByJobName("DONG_BO_HO_SO");
//            //Cron Trigger
//            boolean status = this.schedulerService.updateCronJob(jobName, info.getSchedule_time(), cronExpression);
//            if (status) {
//                info.setJobName("DONG_BO_HO_SO");
//                info.setCronExpression(cronExpression);
//                info.setSchedule_time(new Date());
//                this.schedulerRepository.save(info);
//                return new ResponseEntity<>(true, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(false, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(false, HttpStatus.OK);
//    }
//	
	@RequestMapping(value = "/job/stop", method = RequestMethod.GET)
    public ResponseEntity<Boolean> stopJob(
            @RequestParam("jobName") String jobName,
            @RequestParam("jobGroup") String jobGroup
    ) {

        //Job Name is mandatory
        if (jobName == null || jobName.trim().equals("")) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

        //stop Job
        if (this.schedulerService.isJobWithNamePresent(jobName, jobGroup)) {
            //Cron Trigger
        	this.schedulerService.stopJob(jobName, jobGroup);
        	 return new ResponseEntity<>(true, HttpStatus.OK);
           
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }
	

}
