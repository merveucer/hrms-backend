package io.kodlama.hrms.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Image;

public interface ImageService extends BaseEntityService<Image> {

	Result upload(int userId, MultipartFile file);

	DataResult<Image> getByUserId(int userId);

}
