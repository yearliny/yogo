package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.repository.UserRepository;
import ga.yuan.yogo.service.UserService;
import ga.yuan.yogo.utils.RegexUtil;
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
    public Optional<User> find(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByNameOrEmail(String nameOrEmail) {
        User user;
        if (RegexUtil.isEmail(nameOrEmail)) {
            user = userRepository.findByEmail(nameOrEmail);
        } else {
            user = userRepository.findByName(nameOrEmail);
        }
        return user;
    }

    @Override
    public User save(User user) {
//       保存用户则先加密密码
        if (user.getUid() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }
}
