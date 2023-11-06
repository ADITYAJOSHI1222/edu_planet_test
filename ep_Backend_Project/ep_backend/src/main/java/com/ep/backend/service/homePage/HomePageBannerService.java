package com.ep.backend.service.homePage;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ep.backend.dto.homePage.HomePageBannerDto;
import com.ep.backend.entities.homePage.HomePageBanner;
import com.ep.backend.entities.successStory.ImageStorageProperties;

@Service
public interface HomePageBannerService {
	List<HomePageBannerDto> getAllHomePageBanners();
	HomePageBannerDto addHomePageBanner(HomePageBanner homePageBanner);
	String FileStorageService(ImageStorageProperties imageStorageProperties, MultipartFile file);
	HomePageBannerDto deleteHomePageBanner();
}
