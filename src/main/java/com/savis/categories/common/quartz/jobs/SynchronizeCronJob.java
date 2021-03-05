package com.savis.categories.common.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.savis.categories.service.serviceSchedule;


/**
 * TODO: write you class description here
 *
 * @author
 */

@Service
public class SynchronizeCronJob extends QuartzJobBean {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Value("${url_getInfo}")
	private String url_getInfo;
	@Value("${url_DTNN}")
	private String url_DTNN;
	@Value("${url_hny}")
	private String url_hny;
	@Value("${appId_DKKD}")
	private String appId_DKKD;
	@Value("${url_GPLX}")
	private String url_GPLX;
	@Value("${url_phbh}")
	private String url_phbh;
	@Value("${url_dkkd}")
	private String url_dkkd;
	@Value("${organizationID}")
	private String organizationID;
	@Value("${time}")
	private int time;
	@Value("${unitId}")
	private String unitId;
	@Value("${ma_tinh}")
	private String ma_tinh;
	@Value("${url_dkkd_chiTiet}")
	private String url_dkkd_chiTiet;
	@Value("${appId_HNY}")
	private String appId_HNY;
	@Value("${ghiChuCCHN}")
	private String ghiChuCCHN;
	@Value("${ghiChuCSKCB}")
	private String ghiChuCSKCB;
@Autowired
serviceSchedule serviceSchedule;
	@Override
	protected void executeInternal(JobExecutionContext context) {
		serviceSchedule.check();
		System.out.println("done");
	}

	
}
