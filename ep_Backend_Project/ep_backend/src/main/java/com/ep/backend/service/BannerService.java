package com.ep.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ep.backend.dto.BannerDto;
import com.ep.backend.entities.ImageStorageProperties;


@Service
public interface BannerService {
	
	List<BannerDto> getAllBanners();
	BannerDto addBanner(BannerDto bannerDto);
	String FileStorageService(ImageStorageProperties imageStorageProperties, MultipartFile file);
	BannerDto deleteBanner();
//	void uploadImage(byte[] imageData);
	
}

