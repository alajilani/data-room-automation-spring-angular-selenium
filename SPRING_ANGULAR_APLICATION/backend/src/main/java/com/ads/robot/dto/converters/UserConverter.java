package com.ads.robot.dto.converters;

import com.ads.robot.dto.UserDto;
import com.ads.robot.entities.User;
import com.ads.robot.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private UserRepo userRepo;
    private ModelMapper modelMapper = new ModelMapper();
    public UserDto convertToDto(User user){
        return modelMapper.map(user,UserDto.class);
    }
    public User convertToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }

}
