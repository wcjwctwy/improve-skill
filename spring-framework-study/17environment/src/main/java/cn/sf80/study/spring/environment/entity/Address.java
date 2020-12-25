package cn.sf80.study.spring.environment.entity;

/**
 * @author wangcongjun
 * @date 2020/12/25 9:27
 */
public class Address {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                '}';
    }
}
