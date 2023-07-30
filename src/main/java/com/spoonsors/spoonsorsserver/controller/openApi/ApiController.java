package com.spoonsors.spoonsorsserver.controller.openApi;


import com.spoonsors.spoonsorsserver.entity.api.ApiDto;
import com.spoonsors.spoonsorsserver.service.openApi.ApiService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {
    private final ApiService apiService;
//    private String serviceKey = "54644966ea4145559fc0";
//    private String serviceId = "COOKRCP01";
    //모든 데이터 출력
    @GetMapping("/recipe/findAll")
    public List<ApiDto> allAllApi() throws IOException{

        return apiService.findAll();

    }

    @GetMapping("/recipe/findByName")
    public List<ApiDto> findByNameApi(@RequestParam String RCP_NM) throws IOException{

        return apiService.findByName(RCP_NM.replaceAll(" ",""));

    }


}
