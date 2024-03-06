package com.hnv99.hmall.serivce.impl;

import com.hnv99.hmall.common.security.util.JwtTokenUtil;
import com.hnv99.hmall.model.entity.MemberDetails;
import com.hnv99.hmall.model.entity.User;
import com.hnv99.hmall.model.form.UserLoginForm;
import com.hnv99.hmall.model.form.UserRegisterForm;
import com.hnv99.hmall.repository.UserRepository;
import com.hnv99.hmall.serivce.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean register(UserRegisterForm formData) {
        String email = formData.getEmail();
        String password = formData.getPassword();
        if (!isEmailAvailable(email)) {
            throw new IllegalArgumentException("Email is already taken");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return true;
    }

    @Override
    public String login(UserLoginForm formData) {
        String email = formData.getEmail();
        String password = formData.getPassword();
        String token = "";

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        MemberDetails memberDetails = new MemberDetails(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token = jwtTokenUtil.generateToken(memberDetails);

        return token;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }
}
