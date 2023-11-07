package com.ep.backend.controllers.successStory;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ep.backend.dto.successStory.BannerDto;
import com.ep.backend.entities.successStory.Banner;
import com.ep.backend.entities.successStory.ImageStorageProperties;
import com.ep.backend.service.successStory.BannerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/banners")
public class BannerController {
	
	@Autowired
	BannerService bannerService;
	
	@Autowired
	private ObjectMapper objectMapper;
	ImageStorageProperties imageStorageProperties = new ImageStorageProperties();
	
	
	@PostMapping
	public ResponseEntity<String> createBanner (@RequestParam("model") String jsonObject, @RequestParam MultipartFile file){
//		ImageStorageProperties imageStorageProperties = new ImageStorageProperties();
		BannerDto bannerDto = new BannerDto();
		String responseString = null;
		imageStorageProperties.setUploadDir("./src/main/resources/uploads");
		try {
			String fileName = bannerService.FileStorageService(imageStorageProperties, file);
		    
		    System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path(fileName).toUriString());
		    
		    Gson gson = new Gson();
		    bannerDto = gson.fromJson(jsonObject, BannerDto.class);
    
		    BannerDto createdBanner = this.bannerService.addBanner(bannerDto);
		    objectMapper.readValue(jsonObject, Banner.class);
		    
		    responseString = "Succesfull \n"+ "File: "+fileName+"\nBannerData: "+jsonObject;
		}catch (JsonProcessingException e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to process JSON Data");
		}
		return ResponseEntity.ok(responseString);
	}
	 
	@GetMapping
	public List<BannerDto> getBanners() {
	    List<BannerDto> bannerDto = bannerService.getAllBanners();
	    return bannerDto;
	}
	
	@GetMapping("/bannerImage")
	public ResponseEntity<byte[]> getBannerImage() throws IOException {
//		InputStream in = getClass().getResourceAsStream("/uploads/BannerImage.jpg");
//	    return IOUtils.toByteArray(in);
		// Specify the path to your image file within the resources directory
        String imagePath = "./uploads/BannerImage.jpg";

        // Load the image file as a resource
        Resource resource = new ClassPathResource(imagePath);

        // Check if the resource exists
        if (resource.exists()) {
            // Read the image file as an InputStream
            try (InputStream in = resource.getInputStream()) {
                // Read the image data into a byte array
                byte[] imageData = org.apache.commons.io.IOUtils.toByteArray(in);

                // Return the image data as a response with the appropriate content type
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Adjust the media type based on your image format
                        .body(imageData);
            }
        } else {
            // Resource not found
            return ResponseEntity.notFound().build();
        }
    }
	
	@DeleteMapping
	public ResponseEntity<String> deleteBanner() {
	    BannerDto bannerDto = bannerService.deleteBanner(); // Replace with your service method
	    
	    if(bannerDto != null) {
	    	
		    return ResponseEntity.ok("Banner Delete Successfully");
	    }
	    else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Banner Not Found");	    
	} 
  }
	
	 
}



