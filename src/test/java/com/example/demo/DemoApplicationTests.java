package com.example.demo;

import com.example.demo.exceptions.CacheException;
import com.example.demo.ipcache.IpContextHolderImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.of;

class DemoApplicationTests {

	static IpContextHolderImpl ipContextHolder = new IpContextHolderImpl(6, 30);

	private static Stream<Arguments> getРазные_айпи_с_разным_интервалом() {
		return Stream.of(
				of("192.168.0.1", 100, 62, true),
				of("192.168.0.2", 180, 62, true),
				of("192.168.0.3", 400, 37, false),
				of("192.168.0.4", 800, 37, false)
		);
	}

	@MethodSource("getРазные_айпи_с_разным_интервалом")
	@ParameterizedTest()
	void contextLoads(String ip, long milis, Integer count, Boolean hasError) {
		Boolean error = false;
		try {
			runCheck(ip, milis, count);
		} catch (CacheException e){
			error = true;
		} catch (InterruptedException e){
			assert false;
		}
		Assertions.assertEquals(hasError, error);
	}

	private void runCheck(String ip, long milis, Integer count) throws InterruptedException {
		for (int i = 0; i < count; i++) {
			ipContextHolder.checkIp(ip);
			Thread.sleep(milis);
		}
	}
}
