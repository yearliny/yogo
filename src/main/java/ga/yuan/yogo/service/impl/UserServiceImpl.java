package ga.yuan.yogo.service.impl;

import ga.yuan.yogo.repository.UserRepository;
import ga.yuan.yogo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 如果用户数量大于 0，返回 true。用于判断博客是否是第一次运行
     *
     * @return bool
     */
    @Override
    public boolean existsUser() {
        return userRepository.count() > 0;
    }
}
