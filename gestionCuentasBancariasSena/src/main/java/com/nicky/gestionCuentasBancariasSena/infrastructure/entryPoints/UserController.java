package com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints;

import com.nicky.gestionCuentasBancariasSena.domain.usecases.UserUseCase;
import com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.request.UserRequestDTO;
import com.nicky.gestionCuentasBancariasSena.infrastructure.entryPoints.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO user){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(UserResponseDTO.fromDomain(userUseCase.registerUser(user.toDomain())));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> loginUser(@RequestBody UserRequestDTO user){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserResponseDTO.fromDomain(userUseCase.loginUser(user.toDomain())));
    }
}
