package com.example.mapper;

import com.example.dto.CreateUserProfileRequest;
import com.example.dto.UpdateUserProfileRequest;
import com.example.dto.UserProfileDto;
import com.example.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-22T15:47:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(CreateUserProfileRequest createRequest) {
        if ( createRequest == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( createRequest.getEmail() );
        user.setPassword( createRequest.getPassword() );
        user.setFullName( createRequest.getFullName() );
        user.setPhone( createRequest.getPhone() );
        user.setBirthDate( createRequest.getBirthDate() );
        user.setBio( createRequest.getBio() );

        user.setRoles( toRoles(createRequest.getRoles()) );

        return user;
    }

    @Override
    public UserProfileDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserProfileDto userProfileDto = new UserProfileDto();

        userProfileDto.setId( user.getId() );
        userProfileDto.setEmail( user.getEmail() );
        userProfileDto.setFullName( user.getFullName() );
        userProfileDto.setPhone( user.getPhone() );
        userProfileDto.setBirthDate( user.getBirthDate() );
        userProfileDto.setBio( user.getBio() );
        userProfileDto.setCreatedAt( user.getCreatedAt() );
        userProfileDto.setUpdatedAt( user.getUpdatedAt() );

        userProfileDto.setRoles( toRoleStrings(user.getRoles()) );

        return userProfileDto;
    }

    @Override
    public void updateEntityFromDto(UpdateUserProfileRequest update, User user) {
        if ( update == null ) {
            return;
        }

        user.setFullName( update.getFullName() );
        user.setPhone( update.getPhone() );
        user.setBirthDate( update.getBirthDate() );
        user.setBio( update.getBio() );
    }
}
