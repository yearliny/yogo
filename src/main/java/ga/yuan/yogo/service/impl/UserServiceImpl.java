package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.model.entity.User;
import ga.yuan.yogo.repository.UserRepository;
import ga.yuan.yogo.service.UserService;
import ga.yuan.yogo.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> find(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
//        设定用户注册时间
        user.setCreated(new Date());
//        BCrypt 加密密码
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return userRepository.save(user);
    }

    /**
     * 验证用户是否正确
     * @param nameOrEmail user name or email
     * @param password user password
     * @return bool
     */
    @Override
    public boolean verifyUser(String nameOrEmail, String password) {
        boolean verified;

        if (ValidationUtil.isEmail(nameOrEmail)) {
            Optional<User> user = userRepository.findByEmail(nameOrEmail);
            verified = user.map(u -> u.getPassword().equals(password)).orElse(false);
        } else {
            Optional<User> user = userRepository.findByName(nameOrEmail);
            verified = user.map(u -> u.getPassword().equals(password)).orElse(false);
        }
        return verified;
    }

    @Override
    public boolean existsById(long id) {
        return userRepository.existsById(id);
    }
}
