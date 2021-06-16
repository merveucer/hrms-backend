package io.kodlama.hrms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.kodlama.hrms.business.adapters.cloudinary.CloudStorageService;
import io.kodlama.hrms.business.adapters.cloudinary.CloudinaryServiceAdapter;

@Configuration
public class CloudinaryConfig {

	@Bean
	public Cloudinary cloudinaryAccount() {
		return new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "",
				"api_key", "",
				"api_secret", ""
				));
	}

	@Bean
	public CloudStorageService cloudStorageService() {
		return new CloudinaryServiceAdapter(cloudinaryAccount());
	}

}
