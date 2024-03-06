package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.form.UserLoginForm;
import com.hnv99.hmall.model.form.UserRegisterForm;

public interface AuthService {
    boolean register(UserRegisterForm formData);
    String login(UserLoginForm formData);
    void logout();
    boolean isUsernameAvailable(String username);
    boolean isEmailAvailable(String email);
}
