package com.ep.backend.dto.successStory;

import org.springframework.stereotype.Component;

import com.ep.backend.entities.successStory.Banner;



@Component
public class BannerDtoToEntity {
	
	public Banner dtoToBanner(BannerDto bannerDto) {
		
		Banner banner = new Banner();
		banner.setBanner_heading(bannerDto.getBanner_heading());
		banner.setBanner_text(bannerDto.getBanner_text());
		banner.setButton_text(bannerDto.getButton_text());
		banner.setButton_url(bannerDto.getButton_url());
		banner.setStatus(bannerDto.isStatus());
		return banner;
	}

	public BannerDto bannerToDto (Banner banner) {
		BannerDto bannerDto = new BannerDto();
		bannerDto.setBanner_heading(banner.getBanner_heading());
		bannerDto.setBanner_text(banner.getBanner_text());
		bannerDto.setButton_text(banner.getButton_text());
		bannerDto.setButton_url(banner.getButton_url());
		bannerDto.setStatus(banner.isStatus());
		return bannerDto;
	}

}
