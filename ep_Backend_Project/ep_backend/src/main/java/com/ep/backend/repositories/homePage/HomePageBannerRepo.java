package com.ep.backend.repositories.homePage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ep.backend.entities.homePage.HomePageBanner;

@Repository
public interface HomePageBannerRepo extends JpaRepository<HomePageBanner, Integer>{

}
