package com.ep.backend.entities.homePage;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HomePageBanner {
	
	private int homePageBannerId;
	private String homePageBannerHeading;
	private String homePageBannerText;
	private String homePageBannerButtonText;
	private String homePageBannerButtonurl;
	private boolean status;
}
