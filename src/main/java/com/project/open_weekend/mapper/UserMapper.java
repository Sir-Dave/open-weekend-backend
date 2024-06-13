package com.project.open_weekend.mapper;

import com.project.open_weekend.user.User;
import com.project.open_weekend.user.UserDto;
import com.project.open_weekend.util.Util;

public class UserMapper {

    public UserDto mapUserToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber() == null ? "" : user.getPhoneNumber(),
                Util.getFormattedDate(user.getDateJoined()),
                user.getRole()
        );
    }
}
