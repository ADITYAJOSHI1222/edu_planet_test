package com.ep.backend.services.Impl;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ep.backend.dto.BannerDto;
import com.ep.backend.dto.BannerDtoToEntity;
import com.ep.backend.entities.Banner;
import com.ep.backend.entities.ImageStorageProperties;
import com.ep.backend.repositories.BannerRepo;
import com.ep.backend.service.BannerService;


@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerRepo bannerRepo;
	@Autowired
	private BannerDtoToEntity bannerDtoToEntity;
	
	@Override
	public BannerDto addBanner(BannerDto bannerDto) {
		Banner bannerEntity = new Banner();
		List<Banner> banners = this.bannerRepo.findAll();
		
		if (banners.isEmpty()) {
			bannerEntity = bannerDtoToEntity.dtoToBanner(bannerDto);
		} else {
			bannerEntity = banners.get(0);
			if (bannerDto.getBanner_heading() != null) {
				bannerEntity.setBanner_heading(bannerDto.getBanner_heading());
			}
			if (bannerDto.getBanner_text() != null) {
				bannerEntity.setBanner_text(bannerDto.getBanner_text());
			}
			if (bannerDto.getButton_text() != null) {
				bannerEntity.setButton_text(bannerDto.getButton_text());
			}
			if (bannerDto.getButton_url() != null) {
				bannerEntity.setButton_url(bannerDto.getButton_url());
			}
			if (bannerDto.isStatus()) {
				bannerEntity.setStatus(bannerDto.isStatus());
			}
		}
		
		bannerEntity = this.bannerRepo.save(bannerEntity);
		
		return this.bannerDtoToEntity.bannerToDto(bannerEntity);
	}
	
	@Override
	public String FileStorageService(ImageStorageProperties imageStorageProperties, MultipartFile file) {
		final Path imageStorageLocation;
		
		imageStorageLocation = Paths.get(imageStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(imageStorageLocation);
		}catch(Exception ex){
			throw new RuntimeException("Couldn't create the directory where the upload files will be saved.", ex);
		}
		
//		String fileName = StringUtils.getFilenameExtension(file.getOriginalFilename());
		String fileName = "BannerImage.jpg";
		
		try {
			if(fileName.contains("..")) {
				throw new RuntimeException("Sorry! File name which contains invalid path sequence " + fileName);
			}
			Path targetLocation = imageStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		}catch(Exception ex) {
			throw new RuntimeException("Couldn't create the directory where the upload files will be saved." + fileName, ex);
		}		
	}
	
	@Override
	public List<BannerDto> getAllBanners(){
		List<Banner> banners = this.bannerRepo.findAll();
		List<BannerDto> bannerDto = banners.stream().map(banner->this.bannerDtoToEntity.bannerToDto(banner)).collect(Collectors.toList());
		return bannerDto;
	}
	
	public BannerDto deleteBanner() {
		List<Banner> banners = this.bannerRepo.findAll();
				
		if (banners.size() > 0 ) {
			this.bannerRepo.deleteAll();
		} else {
			return null;
		}
		
		return this.bannerDtoToEntity.bannerToDto(banners.get(0));
	}
 
}




