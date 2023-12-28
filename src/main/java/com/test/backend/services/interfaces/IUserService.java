package com.test.backend.services.interfaces;

import com.test.backend.entities.DTO.AuthResponseDto;
import com.test.backend.entities.DTO.LoginDto;
import com.test.backend.entities.User;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserService {
    String addUser(User userEntity);

    User getUser(int id);

    AuthResponseDto login(@RequestBody LoginDto loginDto);
}
