package com.benjaminleb.mowitnow.infra.rest;

import com.benjaminleb.mowitnow.infra.rest.response.RestApiResponse;
import com.benjaminleb.mowitnow.mowing.MowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RestController
public class MowingLauncherResource {

    private final MowingService mowingService;

    @PostMapping("/upload")
    public RestApiResponse<String> uploadMowingInstructions(@RequestParam("file") MultipartFile file) {
        return RestApiResponse.ok(mowingService.launchMowing(file));
    }

}
