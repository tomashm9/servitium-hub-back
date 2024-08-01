package com.thm.gw.dtos.auth;

import com.thm.gw.dtos.user.UserShortDTO;
import com.thm.gw.entities.User;
import com.thm.gw.entities.Role;

import java.util.Set;
import java.util.stream.Collectors;

public record UserTokenDTO(
        UserShortDTO user,
        Set<String> roles,
        String accessToken

) {
    public static UserTokenDTO fromEntityWithToken(User user, String token) {
        return new UserTokenDTO(
                new UserShortDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhoneNumber(),
                        user.getContactEmail(),
                        user.getGender(),
                        user.getBirthDate()
                ),
                user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
                token
        );
    }
}