package com.savis.categories.common.quartz.component;



import org.quartz.SchedulerException;
import org.quartz.spi.InstanceIdGenerator;

import java.util.UUID;

/**
 * @author
 */
public class CustomQuartzInstanceIdGenerator implements InstanceIdGenerator {

    @Override
    public String generateInstanceId() throws SchedulerException {
        try {
            return UUID.randomUUID().toString();
        } catch (Exception ex) {
            throw new SchedulerException("Couldn't generate UUID!", ex);
        }
    }

}