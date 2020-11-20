package cn.sf80.study.spring.bean.factory;


import cn.sf80.study.spring.ioc.overview.domain.User;

/**
 * {@link User} 工厂类
 *
 * @author  wangcongjun
 * @since
 */
public interface UserFactory {

    default User createUser() {
        return User.createUser();
    }
}