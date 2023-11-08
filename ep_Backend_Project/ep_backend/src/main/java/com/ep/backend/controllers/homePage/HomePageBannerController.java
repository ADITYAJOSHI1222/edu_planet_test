//package com.ep.backend.controllers.homePage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.ep.backend.dto.homePage.HomePageBannerDto;
//import com.ep.backend.entities.successStory.ImageStorageProperties;
//import com.ep.backend.service.homePage.HomePageBannerService;
//import com.google.gson.Gson;
//
//@RestController
//@RequestMapping("/api/homePageBanners")
//public class HomePageBannerController {
//
//    @Autowired
//    HomePageBannerService homePageBannerService;
//
//    ImageStorageProperties homePageImagestorageProperties = new ImageStorageProperties();
//
//    @PostMapping
//    public ResponseEntity<String> createHomePageBanner(
//        @RequestParam("model") String jsonObject,
//        @RequestParam MultipartFile homePageBannerFile
//    ) {
//        String responseString = null;
//
//        homePageImagestorageProperties.setUploadDir("./src/main/resources/homePageBanneruploads");
//
//        // Parse the JSON into a HomePageBannerDto
//        Gson gson = new Gson();
//        HomePageBannerDto homePageBannerDto = gson.fromJson(jsonObject, HomePageBannerDto.class);
//
//        // Store the banner file
//        String homePageFileName = homePageBannerService.FileStorageService(homePageImagestorageProperties, homePageBannerFile);
//
//        System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path(homePageFileName).toUriString());
//
//        // Call the service method with the created HomePageBannerDto
//        HomePageBannerDto createdBannerDto = homePageBannerService.addHomePageBanner(homePageBannerDto);
//
//        responseString = "Successful\nFile: " + homePageFileName + "\nBannerData: " + jsonObject;
//
//        return ResponseEntity.ok(responseString);
//    }
//}
