package com.example.demo.Controllers;

import com.example.demo.Services.UrlShortenerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    @Autowired
    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @Operation(summary = "Save a shortened URL", description = "Saves a full URL and returns a shortened version.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "URL saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid URL provided"),
            @ApiResponse(responseCode = "409", description = "URL already exists")
    })
    @PostMapping("/shorten")
    public ResponseEntity<String> saveShortUrl(@RequestBody String fullUrl) {
        String shortenedUrl = urlShortenerService.saveShortUrl(fullUrl);
        return ResponseEntity.ok(shortenedUrl);
    }

    @Operation(summary = "Get a shortened URL", description = "Retrieves a shortened URL based on the provided full URL.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shortened URL retrieved"),
            @ApiResponse(responseCode = "404", description = "No shortened URL found for provided full URL")
    })
    @GetMapping("/getShortUrl")
    public ResponseEntity<String> getShortUrl(@RequestParam("url") String fullUrl) {
        String shortenedUrl = urlShortenerService.getShortUrl(fullUrl);
        return ResponseEntity.ok(shortenedUrl);
    }

}
