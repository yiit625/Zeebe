package com.example.zebeedemo.Services;

import com.example.zebeedemo.Models.OrderModel;
import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.response.DeploymentEvent;
import io.zeebe.client.api.response.WorkflowInstanceEvent;
import io.zeebe.client.api.worker.JobWorker;


public interface Service1 {

    //Zebee Sunucusuna bağlanıyorum
    ZeebeClient connecting();

    // Daha önce Zebee'nin uygulamasında gerçeklediğim workflow'u deploy ediyorum
    DeploymentEvent deployment(ZeebeClient client);

    //Instance yaratıyorum
    WorkflowInstanceEvent creatingInstance(ZeebeClient client, OrderModel orderModel);

    //Cluster yapısıyla çalıştıpından broker'ın yeni is alması icin varolanı kapatıyorum
    void closing(ZeebeClient client);

    //Job Oluşturma
    JobWorker jobworker(ZeebeClient client, String type , Integer numberOfMicroservice);

    JobWorker hizliIlerleme(OrderModel orderModel);



}
