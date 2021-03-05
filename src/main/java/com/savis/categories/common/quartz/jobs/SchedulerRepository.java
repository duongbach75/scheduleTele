package com.savis.categories.common.quartz.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.savis.categories.common.quartz.enitiy.SchedulerJobInfo;


/**
 * TODO: write you class description here
 *
 * @author
 */

@Repository
public interface SchedulerRepository extends JpaRepository<SchedulerJobInfo, Long> {
	SchedulerJobInfo findByJobName(String jobName);
}
