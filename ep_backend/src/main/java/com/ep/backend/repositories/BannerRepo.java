package com.ep.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ep.backend.entities.Banner;

public interface BannerRepo extends JpaRepository<Banner, Integer> {
}