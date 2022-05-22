package me.study.msa.userservice.service;

import me.study.msa.userservice.dto.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDto);
}
