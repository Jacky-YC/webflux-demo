package com.example.demo.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author jacky
 * @date 2021/4/7
 */
@RestController
public class HelloController {

	@RequestMapping("/test")
	public Mono<String> hello(ServerHttpRequest request) throws UnknownHostException {
		System.out.println("request id: " + request.getId());
		System.out.println("remote address: " + request.getRemoteAddress());
		System.out.println("URI: " + request.getURI());
		return Mono.just("Application Name: myapp:v1 ; Hostname: " + InetAddress.getLocalHost().getHostName());
	}

	@RequestMapping("/api/v1/bpm/hello")
	public Mono<String> bpmHello(ServerHttpRequest request) {
		System.out.println("request id: " + request.getId());
		System.out.println("remote address: " + request.getRemoteAddress());
		System.out.println("URI: " + request.getURI());
		return Mono.just("Hello, BPM~");
	}

}
