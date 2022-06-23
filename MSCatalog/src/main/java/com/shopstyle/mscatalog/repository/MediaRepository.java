package com.shopstyle.mscatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopstyle.mscatalog.entities.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

}
