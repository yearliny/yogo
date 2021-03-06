package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.UserDO;
import ga.yuan.yogo.repository.UserRepository;
import ga.yuan.yogo.service.UserService;
import ga.yuan.yogo.util.RegexUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDO findByNameOrEmail(String nameOrEmail) {
        if (RegexUtil.isEmail(nameOrEmail)) {
            return userRepository.findByEmail(nameOrEmail);
        }
        return userRepository.findByName(nameOrEmail);
    }

    @Override
    public UserDO save(UserDO user) {
        // 如果保存新用户，先加密密码
        if (user.getUid() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }
}
