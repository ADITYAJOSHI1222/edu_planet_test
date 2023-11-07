package com.ep.backend.services.Impl.homePage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ep.backend.dto.homePage.HomePageBannerDto;
import com.ep.backend.dto.homePage.HomePageBannerToEntity;
import com.ep.backend.entities.homePage.HomePageBanner;
import com.ep.backend.entities.successStory.ImageStorageProperties;
import com.ep.backend.repositories.homePage.HomePageBannerRepo;
import com.ep.backend.service.homePage.HomePageBannerService;

@Service
public class HomePageBannerServiceImpl implements HomePageBannerService{
	
	@Autowired
	private HomePageBannerRepo homePageBannerRepo;
	@Autowired
	private HomePageBannerToEntity homePageBannerToEntity;
	
	public HomePageBannerDto addHomePageBanner(HomePageBannerDto homePageBannerDto) {
		
		HomePageBanner homePageBannerEntity = new HomePageBanner();
		List<HomePageBanner> homePageBanners = this.homePageBannerRepo.findAll();
		
		if(homePageBanners.isEmpty()) {
			homePageBannerEntity = homePageBannerToEntity.dtoToHomePageBanner(homePageBannerDto);
		}
		else {
			homePageBannerEntity = homePageBanners.get(0);
			if (homePageBannerDto.getHomePageBannerHeading() != null) {
				homePageBannerEntity.setHomePageBannerHeading(homePageBannerDto.getHomePageBannerHeading());
			}
			if(homePageBannerDto.getHomePageBannerText() != null) {
				homePageBannerDto.setHomePageBannerText(homePageBannerEntity.getHomePageBannerText());
			}
			if(homePageBannerDto.getHomePageBannerButtonText() != null ) {
				homePageBannerEntity.setHomePageBannerButtonText(homePageBannerDto.getHomePageBannerText());
			}
			if (homePageBannerDto.getHomePageBannerButtonurl() != null) {
				homePageBannerEntity.setHomePageBannerButtonurl(homePageBannerDto.getHomePageBannerButtonurl());
			}
			if (homePageBannerDto.isStatus()) {
				homePageBannerEntity.setStatus(homePageBannerDto.isStatus());
			}
		}
		
		homePageBannerEntity = this.homePageBannerRepo.save(homePageBannerEntity);
		
		return this.homePageBannerToEntity.homePageBannerToDto(homePageBannerEntity);
	}


	
	@Override
	public String FileStorageService(ImageStorageProperties homepageImageStorageProperties, MultipartFile homePageBannerfile) {
		final Path imageStorageLocation;
		
		imageStorageLocation = Paths.get(homepageImageStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		
		try {
			Files.createDirectories(imageStorageLocation);
		}
		catch(Exception ex) {
			throw new RuntimeException("Coundn't not create the directory where the upload files will be saved.", ex);
		}
		
		String HomePagefileName = "HomePageBannerImage.jpg" ;
		
		try {
			if (HomePagefileName.contains("..")) {
				throw new RuntimeException("Sorry! File name which contains invalid path sequence" + HomePagefileName);
			}
			
			Path HomePageTargetLocation = imageStorageLocation.resolve((Path) homePageBannerfile) ;
			Files.copy(homePageBannerfile.getInputStream(), HomePageTargetLocation, StandardCopyOption.REPLACE_EXISTING);
			return HomePagefileName;
			} catch(Exception ex) {
				throw new RuntimeException("Couldn't create the directory where the upload files will be saved." + HomePagefileName, ex);
			}
		}
	}

