package com.benjaminleb.mowitnow.infra.rest;

import com.benjaminleb.mowitnow.infra.rest.response.RestApiResponse;
import com.benjaminleb.mowitnow.mowing.MowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
public class MowingLauncherResource {

    private final MowingService mowingService;

    @PostMapping("/upload")
    public RestApiResponse<String> uploadMowingInstructions(@RequestParam("file") MultipartFile file) {
        return RestApiResponse.ok(mowingService.launchMowing(file));
    }

    @GetMapping("/test2")
    public String test2() {
        String instructions = "5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA";
        return instructions.matches("((\\d \\d)( (\\d \\d) ([NSWE]) ([ADG])+)+)") ? "yep" : "nope";
    }
}
