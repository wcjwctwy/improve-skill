package cn.sf80.study.spring.ioc.overview.domain;

import cn.sf80.study.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author wangcongjun
 * @since
 */
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}