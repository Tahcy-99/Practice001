package org.example.practice001.user;

import lombok.RequiredArgsConstructor;
import org.example.practice001.common.model.BaseResponse;
import org.example.practice001.common.model.BaseResponseStatus;
import org.example.practice001.user.model.AuthUserDetails;
import org.example.practice001.user.model.UserDto;
import org.example.practice001.utils.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto.SignupReq dto) {
        userService.signup(dto);

        return ResponseEntity.ok(BaseResponse.success("등록 성공"));
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto.LoginReq dto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword(), null);

        Authentication authentication = authenticationManager.authenticate(token);
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        if(user != null) {
            String jwt = jwtUtil.createToken(user.getIdx(), user.getUsername(), user.getRole());
            return ResponseEntity.ok().header("Set-Cookie", "ATOKEN=" + jwt + "; Path=/")
                    .body(BaseResponse.success(user));
        }

        return ResponseEntity.ok(BaseResponse.success(BaseResponseStatus.LOGIN_INVALID_USERINFO));
    }

    @GetMapping("/verify")
    public ResponseEntity verify(String uuid) {
        userService.verify(uuid);
        // 인증 성공하면 프론트로 리다이렉트
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(URI.create("http://localhost:5173")).build();
    }
}