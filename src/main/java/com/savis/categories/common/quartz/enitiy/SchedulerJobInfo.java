package com.savis.categories.common.quartz.enitiy;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author
 */
@Getter
@Setter
@Entity
@Table( name = "SCHEDULER_JOB_INFO")
public class SchedulerJobInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name", unique = true)
    private String jobName;

    @Column(name = "job_full_name")
    private String jobFullName;

    @Column(name = "job_group")
    private String jobGroup;

    @Column(name = "job_class")
    private String jobClass;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "repeat_time")
    private Long repeatTime;

    @Column(name = "cron_job")
    private Boolean cronJob;

    @Column(name = "type_syn")
    private String type_syn;

    @Column(name = "month")
    private Long month;

    @Column(name = "year")
    private Long year;

    @Column(name = "day")
    private String day;

    @Column(name = "hour")
    private Long hour;

    @Column(name = "minute")
    private Long  minute;

    @Column(name = "week_day")
    private String week_day;

    @Column(name = "week_time")
    private String week_time;

    @Column(name = "schedule_time")
    private Date schedule_time;
}
