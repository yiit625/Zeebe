package com.example.zeebedemo3.Services;

import io.zeebe.client.ZeebeClient;
import io.zeebe.client.api.worker.JobWorker;

public interface Service {

    //Zebee Sunucusuna bağlanıyorum
    ZeebeClient connecting();

    //Job Oluşturma
    JobWorker jobworker(ZeebeClient client, String type, Integer numberOfMicroservice);

}
