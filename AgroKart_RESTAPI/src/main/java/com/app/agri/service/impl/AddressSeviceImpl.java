package com.app.agri.service.impl;

import java.util.List;
import java.util.Optional;
//import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.agri.dto.AddressDto;
import com.app.agri.dto.BaseDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.dto.UserDto;
//import com.app.agri.dto.ProductDto;
import com.app.agri.entity.Address;
import com.app.agri.entity.BaseEntity;
import com.app.agri.entity.Category;
import com.app.agri.entity.Product;
import com.app.agri.entity.User;
import com.app.agri.exception.AlreadyExistsException;
import com.app.agri.exception.ContentNotFound;
import com.app.agri.repository.AddressRepository;
import com.app.agri.repository.UserRepository;
import com.app.agri.service.AddressService;

@Service
public class AddressSeviceImpl extends ConversionService implements AddressService {

	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<AddressDto> findAddressByUser(int userId)
	{
		List<AddressDto> addressDto = null;

		try {
			List<Address> address = addressRepo.findByUserId(userId);

			if (address != null) {
				addressDto = address.stream().map(a -> (AddressDto) super.convertEntityToDto(a, AddressDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return addressDto;
    }

	@Override
	public AddressDto findAddressByAddressId(int addressId)
	{/*
		AddressDto addressDto = null;

		try {
			Optional <Address> address = addressRepo.findById(addressId);

			if (address != null) {
				addressDto = super.convertEntityToDto(address, AddressDto.class);
						
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return addressDto;*/
		AddressDto addressDto = null;
		try {
			Optional<Address> optionalAddress = addressRepo.findById(addressId);

			if (!optionalAddress.isPresent()) {
				throw new ContentNotFound("Address does not exists");
			} else {
				Address address = optionalAddress.get();
				addressDto =(AddressDto) super.convertEntityToDto(address, AddressDto.class);
				
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return addressDto;
    }
	
	@Override
	public AddressDto addAddress(@Valid AddressDto a) {
		/*
		 * try { //Address address =
		 * addressRepo.findByCategoryName(c.getCategoryName());
		 * 
		 * //if (category != null) { //throw new
		 * AlreadyExistsException("Category is already Exists"); }
		 */
		Address address = (Address) convertDtoToEntity(a, Address.class);

		addressRepo.save(address);
		/*
		 * } catch (Exception e) { throw new RuntimeException(e.getMessage()); }
		 */
		return a;
		
	}
	public Address convertDtoToEntity(BaseDto src, Class<? extends BaseEntity> dest) {
		Address address = null;
		try {
			AddressDto addressDto = (AddressDto) src;

			address = (Address) super.convertDtoToEntity(src, Address.class);


			Optional<User> optionalUser = userRepo.findById(addressDto.getUserId());

			

			if (!optionalUser.isPresent()) {
				throw new ContentNotFound("Invalid User");
			}


			User user = optionalUser.get();

			address.setUser(user);

		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return address;
	}

	@Override
	public boolean deleteAddress(int addressId)
	{
		try {
			Optional<Address> optionalAddress = addressRepo.findById(addressId);

			if (!optionalAddress.isPresent()) {
				throw new ContentNotFound("Address does not exists");
			} else {
				addressRepo.deleteById(addressId);

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return Boolean.TRUE;
	}
}
