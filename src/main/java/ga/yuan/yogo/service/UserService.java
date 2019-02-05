package ga.yuan.yogo.service;

public interface UserService {

    /**
     * 是否存在用户，以此判断博客是否为第一次运行（是否安装）
     *
     * @return Boolean
     */
    boolean existsUser();
}
