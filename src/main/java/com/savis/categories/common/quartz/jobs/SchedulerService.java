package com.savis.categories.common.quartz.jobs;



import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.savis.categories.common.quartz.enitiy.JobSearchForm;
import com.savis.categories.common.quartz.enitiy.SchedulerJobInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO: write you class description here
 *
 * @author
 */

@Service
public interface SchedulerService {

    void startAllSchedulers();

    void scheduleNewJob(SchedulerJobInfo jobInfo);

    void updateScheduleJob(SchedulerJobInfo jobInfo);

    boolean unScheduleJob(String jobName);

    boolean deleteJob(SchedulerJobInfo jobInfo);

    boolean pauseJob(SchedulerJobInfo jobInfo);

    boolean resumeJob(SchedulerJobInfo jobInfo);

    boolean startJobNow(SchedulerJobInfo jobInfo);

    boolean scheduleOneTimeJob(String jobName, String jobGroup, Class<? extends QuartzJobBean> jobClass, Date date);
    boolean scheduleCronJob(String jobName, String jobGroup, Class<? extends QuartzJobBean> jobClass, Date date, String cronExpression);

    boolean updateOneTimeJob(String jobName, Date date);
    boolean updateCronJob(String jobName, Date date, String cronExpression);

    boolean deleteJob(String jobName, String jobGroup);
    boolean pauseJob(String jobName, String jobGroup);
    boolean resumeJob(String jobName, String jobGroup);
    boolean startJobNow(String jobName, String jobGroup);
    boolean isJobRunning(String jobName, String jobGroup);
    List<Map<String, Object>> getAllJobs(JobSearchForm jobSearch);
    boolean isJobWithNamePresent(String jobName, String jobGroup);
    String getJobState(String jobName, String jobGroup);
    boolean stopJob(String jobName, String jobGroup);
    List<Map<String, Object>> jobDetail(String jobName, String jobGroup, String jobStatus);
}
