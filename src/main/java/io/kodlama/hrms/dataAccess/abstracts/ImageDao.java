package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer> {

	Image getByUser_Id(int userId);

}
