package com.example.zeebedemo2.ServiceImpl;


import com.example.zeebedemo2.Services.Service1;
import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.worker.JobWorker;

public class ServiceImpl1 implements Service1 {

    @Override
    public ZeebeClient connecting() {
        final ZeebeClient client = ZeebeClient.newClientBuilder()
                .brokerContactPoint("35.233.11.65:26500")
                .usePlaintext()
                .build();

        System.out.println("Connected");

        return client;
    }

    @Override
    public JobWorker jobworker(ZeebeClient client, String type, Integer numberOfMicroservice) {
        JobWorker jobWorker = client.newWorker()
                .jobType("type")
                .handler((jobClient, job) ->
                {
                    System.out.println("Task" + numberOfMicroservice + "Completed");

                    jobClient.newCompleteCommand(job.getKey())
                            .send()
                            .join();
                })
                .open();

        return jobWorker;
    }
}
