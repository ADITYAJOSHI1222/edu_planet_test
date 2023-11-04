package com.ep.backend.repositories.successStory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ep.backend.entities.successStory.Banner;

public interface BannerRepo extends JpaRepository<Banner, Integer> {
}