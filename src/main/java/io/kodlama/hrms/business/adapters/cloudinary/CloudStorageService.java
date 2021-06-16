package io.kodlama.hrms.business.adapters.cloudinary;

import org.springframework.web.multipart.MultipartFile;

import io.kodlama.hrms.core.utilities.results.DataResult;

public interface CloudStorageService {

	DataResult<?> upload(MultipartFile multipartFile);
	
	DataResult<?> delete(String publicIdOfImage);

}
