package com.example.zebeedemo.ServiceImpl;


import com.example.zebeedemo.Models.OrderModel;
import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.DeploymentEvent;
import io.zeebe.client.api.response.WorkflowInstanceEvent;
import io.zeebe.client.api.worker.JobWorker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.zebeedemo.Services.Service1;

@Service
@Transactional
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
    public DeploymentEvent deployment(ZeebeClient client) {
        final DeploymentEvent deployment = client.newDeployCommand()
                .addResourceFromClasspath("order-process-with-java-client.bpmn")
                .send()
                .join();
        // Versiyon Yayınla//

        // Birden Fazla makine olabilir, Hepsinin ayrı versyonları olacak//
        final int version = deployment.getWorkflows().get(0).getVersion();
        System.out.println("Workflow deployed. Version : " + version);


        return deployment;
    }

    @Override
    public WorkflowInstanceEvent creatingInstance(ZeebeClient client,OrderModel orderModel) {

        final WorkflowInstanceEvent wfInstance  = client.newCreateInstanceCommand()
                .bpmnProcessId("Process_Id")
                .latestVersion()
                .variables(orderModel)
                .send()
                .join();
        final long workflowInstanceEvent = wfInstance.getWorkflowInstanceKey();
        System.out.println("Workflow instance created. Key : " + workflowInstanceEvent);

        return wfInstance;
    }

    @Override
    public void closing(ZeebeClient client) {
        client.close();
        System.out.println("Closed");
    }



    @Override
    public JobWorker jobworker(ZeebeClient client, String type,  Integer numberOfMicroservice) {

        JobWorker jobWorker = client.newWorker()
                .jobType("type")
                .handler((jobClient, job) ->
                {
                    System.out.println("Task " + numberOfMicroservice + " Completed");

                    jobClient.newCompleteCommand(job.getKey())
                            .send()
                            .join();
                })
                .open();

        return jobWorker;
    }

    @Override
    public JobWorker hizliIlerleme(OrderModel orderModel) {

        final ZeebeClient client = ZeebeClient.newClientBuilder()
                .brokerContactPoint("35.233.11.65:26500")
                .usePlaintext()
                .build();

        System.out.println("Connected");


        final DeploymentEvent deployment = client.newDeployCommand()
                .addResourceFromClasspath("microServiceWithExclusiveGate.bpmn")
                .send()
                .join();
        // Versiyon Yayınla//

        // Birden Fazla makine olabilir, Hepsinin ayrı versyonları olacak//
        final int version = deployment.getWorkflows().get(0).getVersion();
        System.out.println("Workflow deployed. Version : " + version);

        final WorkflowInstanceEvent wfInstance  = client.newCreateInstanceCommand()
                .bpmnProcessId("MicroServiceProcess")
                .latestVersion()
                .variables(orderModel)
                .send()
                .join();
        final long workflowInstanceEvent = wfInstance.getWorkflowInstanceKey();
        System.out.println("Workflow instance created. Key : " + workflowInstanceEvent);


        JobWorker jobWorker = client.newWorker()
                .jobType("type")
                .handler((jobClient, job) ->
                {
                    System.out.println("Task 1  Completed");

                    jobClient.newCompleteCommand(job.getKey())
                            .send()
                            .join();
                })
                .open();

        return jobWorker;
    }
}
