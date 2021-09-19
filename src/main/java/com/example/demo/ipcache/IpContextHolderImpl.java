package com.example.demo.ipcache;

import com.example.demo.exceptions.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class IpContextHolderImpl implements IpContextHolder {

    private static final Logger log = LoggerFactory.getLogger(IpContextHolderImpl.class);

    public int timeoutInSeconds;

    public int maxCount;

    private ConcurrentHashMap<String, LinkedList<LocalTime>> ipCacheCounter;

    public IpContextHolderImpl(@Value("${timeoutInSeconds:1}") int timeoutInSeconds, @Value("${maxCount:1}") int maxCount) {
        this.timeoutInSeconds = timeoutInSeconds;
        this.maxCount = maxCount;
        ipCacheCounter = new ConcurrentHashMap();
    }

    public void checkIp(String ip){
        ipCacheCounter.compute(ip, (k, v) -> {
            LinkedList<LocalTime> vals = v;
            if(vals == null)
                vals = new LinkedList<>();

            while (!vals.isEmpty() && vals.getLast().isBefore(LocalTime.now().minusSeconds(timeoutInSeconds))) {
                log.info("Evict for " + ip);
                vals.removeLast();
            }

            if (vals.size() > maxCount) {
                throw new CacheException(ip);
            }
            log.info("Count from " + ip + " is " + vals.size());

            vals.addFirst(LocalTime.now());
            return vals;
        });

    }
}
