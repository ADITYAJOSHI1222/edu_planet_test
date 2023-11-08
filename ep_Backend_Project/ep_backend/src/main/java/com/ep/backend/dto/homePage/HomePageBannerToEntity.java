//package com.ep.backend.dto.homePage;
//
//import org.springframework.stereotype.Component;
//
//import com.ep.backend.entities.homePage.HomePageBanner;
//
//@Component
//public class HomePageBannerToEntity {
//	
//	public HomePageBanner dtoToHomePageBanner(HomePageBannerDto homePageBannerDto) {
//		HomePageBanner homePageBanner = new HomePageBanner();
//		homePageBanner.setHomePageBannerHeading(homePageBannerDto.getHomePageBannerHeading());
//		homePageBanner.setHomePageBannerText(homePageBannerDto.getHomePageBannerButtonText());
//		homePageBanner.setHomePageBannerButtonText(homePageBannerDto.getHomePageBannerButtonText());
//		homePageBanner.setHomePageBannerButtonurl(homePageBannerDto.getHomePageBannerButtonurl());
//		homePageBanner.setStatus(homePageBannerDto.isStatus());
//		
//		return homePageBanner;
//	}
//	
//	public HomePageBannerDto homePageBannerToDto(HomePageBanner homePageBanner) {
//		
//		HomePageBannerDto homePageBannerDto = new HomePageBannerDto();
//		homePageBannerDto.setHomePageBannerHeading(homePageBanner.getHomePageBannerHeading());
//		homePageBannerDto.setHomePageBannerText(homePageBanner.getHomePageBannerText());
//		homePageBannerDto.setHomePageBannerButtonText(homePageBanner.getHomePageBannerButtonText());
//		homePageBannerDto.setHomePageBannerButtonurl(homePageBanner.getHomePageBannerButtonurl());
//		homePageBannerDto.setStatus(homePageBanner.isStatus());
//		
//		return homePageBannerDto;
//	}
//}
