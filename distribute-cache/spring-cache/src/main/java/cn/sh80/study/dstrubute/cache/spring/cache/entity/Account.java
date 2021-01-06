package cn.sh80.study.dstrubute.cache.spring.cache.entity;

/**
 * @author wangcongjun
 * @date 2021/1/6 15:45
 */
public class Account {
    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                '}';
    }
}
