package org.example.practice001.user;

import lombok.RequiredArgsConstructor;
import org.example.practice001.common.exception.BaseException;
import org.example.practice001.user.model.AuthUserDetails;
import org.example.practice001.user.model.EmailVerify;
import org.example.practice001.user.model.User;
import org.example.practice001.user.model.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.example.practice001.common.model.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final EmailVerifyRepository emailVerifyRepository;

    public UserDto.SignupRes signup(UserDto.SignupReq dto) {

        // 이메일 중복 확인
        if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw BaseException.from(SIGNUP_DUPLICATE_EMAIL);
        }


        User user = dto.toEntity();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

        // 메일 전송
        String uuid = UUID.randomUUID().toString();
        emailService.sendWelcomeMail(uuid, dto.getEmail());

        // 메일 전송 내역 저장
        EmailVerify emailVerify = EmailVerify.builder().email(dto.getEmail()).uuid(uuid).build();
        emailVerifyRepository.save(emailVerify);

        return UserDto.SignupRes.from(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> BaseException.from(LOGIN_INVALID_USERINFO)
        );

        return AuthUserDetails.from(user);
    }

    public void verify(String uuid) {
        EmailVerify emailVerify = emailVerifyRepository.findByUuid(uuid).orElseThrow(
                () -> BaseException.from(SIGNUP_INVALID_UUID)
        );
        User user = userRepository.findByEmail(emailVerify.getEmail()).orElseThrow();
        user.setEnable(true);
        userRepository.save(user);
    }
}
