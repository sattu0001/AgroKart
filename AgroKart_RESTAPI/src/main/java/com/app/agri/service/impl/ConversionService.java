package com.app.agri.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.agri.dto.AddressDto;
import com.app.agri.dto.BaseDto;
import com.app.agri.entity.BaseEntity;

public class ConversionService {

	@Autowired
	private ModelMapper modelMapper;

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public BaseDto convertEntityToDto(BaseEntity src, Class<? extends BaseDto> dest) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(src, dest);
	}

	public BaseEntity convertDtoToEntity(BaseDto src, Class<? extends BaseEntity> dest) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(src, dest);
	}

	

}
