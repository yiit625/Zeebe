package com.example.zeebedemo2;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.worker.JobWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ZeebeDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(ZeebeDemo2Application.class, args);

		final ZeebeClient client = ZeebeClient.newClientBuilder()
				.brokerContactPoint("35.233.11.65:26500")
				.usePlaintext()
				.build();

		System.out.println("Connected");

		JobWorker jobWorker = client.newWorker()
				.jobType("microservice2")
				.handler((jobClient, job) ->
				{
					System.out.println("Task 2 Completed");

					jobClient.newCompleteCommand(job.getKey())
							.send()
							.join();
				})
				.open();






	}


}



