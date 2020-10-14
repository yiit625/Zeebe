package com.example.zebeedemo;


import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.ActivatedJob;
import io.zeebe.client.api.response.DeploymentEvent;
import io.zeebe.client.api.response.WorkflowInstanceEvent;
import io.zeebe.client.api.worker.JobClient;
import io.zeebe.client.api.worker.JobHandler;
import io.zeebe.client.api.worker.JobWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ZebeeDemoApplication {




	public static void main(final String[] args) {



		SpringApplication.run(ZebeeDemoApplication.class, args);



/*		//CONNECTING
		final ZeebeClient client = ZeebeClient.newClientBuilder()
				.brokerContactPoint("35.233.11.65:26500")
				.usePlaintext()
				.build();

		System.out.println("Connected");



		//DEPLOYMENT//
		final DeploymentEvent deployment = client.newDeployCommand()
				.addResourceFromClasspath("order-process-with-java-client.bpmn")
				.send()
				.join();

		// Versiyon Yayınla//

		// Birden Fazla makine olabilir, Hepsinin ayrı versyonları olacak//
		final int version = deployment.getWorkflows().get(0).getVersion();
		System.out.println("Workflow deployed. Version : " + version);

		/////CREATE INSTANCE //////
		final WorkflowInstanceEvent wfInstance  = client.newCreateInstanceCommand()
				.bpmnProcessId("Process_Id")
				.latestVersion()
				.send()
				.join();
		final long workflowInstanceEvent = wfInstance.getWorkflowInstanceKey();
		System.out.println("Workflow instance created. Key : " + workflowInstanceEvent);


		//Broker'ı Kapat yeni işi alabilsin//
			client.close();
			System.out.println("Closed");


		//Sirket WorkFlow Icin Deneme
		 ZeebeClient client = ZeebeClient.newClientBuilder()
				.brokerContactPoint("35.233.11.65:26500")
				.usePlaintext()
				.build();

		System.out.println("Connected");

		 Order order = new Order();
		order.setOrderId(31243);

		order.setTotalPrice(120);



		//DEPLOYMENT//
		final DeploymentEvent deployment = client.newDeployCommand()
				.addResourceFromClasspath("microServiceWithExclusiveGate.bpmn")
				.send()
				.join();

		// Versiyon Yayınla//

		// Birden Fazla makine olabilir, Hepsinin ayrı versyonları olacak//
		final int version = deployment.getWorkflows().get(0).getVersion();
		System.out.println("Workflow deployed. Version : " + version);

		/////CREATE INSTANCE //////
		final WorkflowInstanceEvent wfInstance  = client
				.newCreateInstanceCommand()
				.bpmnProcessId("MicroServiceProcess")
				.latestVersion()
				.variables(order)
				.send()
				.join();
		final long workflowInstanceEvent = wfInstance.getWorkflowInstanceKey();
		System.out.println("Workflow instance created. Key : " + workflowInstanceEvent);


		JobWorker jobWorker = client.newWorker()
				.jobType("microservice1")
				.handler((jobClient, job) ->
						{
						System.out.println("Task 1 Completed");

						jobClient.newCompleteCommand(job.getKey())
								.send()
								.join();
						})
				.open();






	}

	private static class DemoJobHandler implements JobHandler {

		@Override
		public void handle(JobClient client, final ActivatedJob job) {
			// read the variables of the job
			final Order order = job.getVariablesAsType(Order.class);
			System.out.println("new job with orderId: " + order.getOrderId());

					// update the variables and complete the job
			order.setTotalPrice(46.50);

			client.newCompleteCommand(job.getKey()).variables(order).send();
		}
	}

	public static class Order {
		private long orderId;
		private double totalPrice;

		public long getOrderId() {
			return orderId;
		}

		public void setOrderId(final long orderId) {
			this.orderId = orderId;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(final double totalPrice) {
			this.totalPrice = totalPrice;
		}

*/
	}


}
