package com.example.zebeedemo.Controllers;

import com.example.zebeedemo.Models.OrderModel;
import com.example.zebeedemo.Services.LanguageService;
import com.example.zebeedemo.Services.Service1;
import com.example.zebeedemo.api.ResponsePayload;
import com.example.zebeedemo.enumaration.ResponseEnum;
import io.zeebe.client.ZeebeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;

@CrossOrigin
@RestController
@RequestMapping("/zebee-demo1")
public class ServiceController {


    @Autowired
    Service1 service1;
    @Autowired
    LanguageService languageService;


    //Zeebe-demo-2 MikroService'den ulaşılması istenen

    @GetMapping("/common")
    public String general() {
        return "common api success";
    }
/*
    @PostMapping(value = "/connecting", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponsePayload connectingHost(HttpServletRequest request,
                                          @RequestHeader("Accept-Language") String lang){

        try {


            ZeebeClient updatedEntity = service1.connecting();

            if (updatedEntity != null)
                return new ResponsePayload(ResponseEnum.OK, languageService.getResourceBundleValue(lang, "ortak.guncelle.basarili"), updatedEntity);
            else
                return new ResponsePayload(ResponseEnum.NOTFOUND, languageService.getResourceBundleValue(lang, "ortak.guncelle.basarisiz"));
        } catch (AccessDeniedException e) {
            return new ResponsePayload(ResponseEnum.BADREQUEST, languageService.getResourceBundleValue(lang, "ortak.rol.guncelle-kullanici"), Boolean.FALSE);
        } catch (DataIntegrityViolationException ex) {
            return new ResponsePayload(ResponseEnum.NOTFOUND, languageService.getResourceBundleValue(lang, "ortak.hata.duplicate_key") + ":" + ex.getLocalizedMessage());
        } catch (Exception ex) {
            return new ResponsePayload(ResponseEnum.NOTFOUND, languageService.getResourceBundleValue(lang, "ortak.beklenmedik_hata") + ":" + ex.getLocalizedMessage());
        }

    }*/

    @PostMapping(path = "/hizli-ilerleme",
            consumes = "application/json",
            produces = "application/json")
    public ResponsePayload hizliIlerleme(@RequestBody OrderModel orderModel){
        return ResponsePayload.success(service1.hizliIlerleme(orderModel));
    }

    @PostMapping(path = "/deployment",
            consumes = "application/json",
            produces = "application/json")
    public ResponsePayload deployment(@RequestBody ZeebeClient client){

        return ResponsePayload.success(service1.deployment(client));
    }



}
