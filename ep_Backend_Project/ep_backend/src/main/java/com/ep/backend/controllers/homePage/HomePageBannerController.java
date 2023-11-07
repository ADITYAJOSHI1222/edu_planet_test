package com.ep.backend.controllers.homePage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ep.backend.dto.homePage.HomePageBannerDto;
import com.ep.backend.entities.homePage.HomePageBanner;
import com.ep.backend.entities.successStory.ImageStorageProperties;
import com.ep.backend.service.homePage.HomePageBannerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/homePageBanners")
public class HomePageBannerController {

	@Autowired
	HomePageBannerService homePageBannerService ;
	@Autowired
	private ObjectMapper homePageObjectMapper;
	
	ImageStorageProperties homePageImagestorageProperties = new ImageStorageProperties();
	
	@PostMapping
	public ResponseEntity<String> createHomePageBanner(@RequestParam("model") String jsonObject , @RequestParam("files") MultipartFile homePageBannerFile) {
		
		HomePageBannerDto homePageBannerDto = new HomePageBannerDto();
		String responseString = null;
		
		homePageImagestorageProperties.setUploadDir("./src/main/resources/homePageBanneruploads");
		
		try {
			String homePageFileName = homePageBannerService.FileStorageService(homePageImagestorageProperties, homePageBannerFile);
			
			System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path(homePageFileName).toUriString());
			
			Gson gson = new Gson();
			HomePageBannerDto createHomePageBanner = this.homePageBannerService.addHomePageBanner(homePageBannerDto);
			homePageObjectMapper.readValue(jsonObject, HomePageBanner.class);
			
			responseString = "Succesfull \n" + "File" +homePageBannerFile + "\n BannerData: " +jsonObject;
		} catch (JsonProcessingException e) {
			return ResponseEntity.ok(responseString);
		}
		return ResponseEntity.ok(responseString);
		
	}
}
