package cn.sf80.study.spring.ioc.overview.container;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * beanFactory 作为IOC容器，没有AOP 资源管理的特性，只是单纯的一个容器
 * 这个和ApplicationContext是有区别的 （有AOP 资源管理的其他特性）
 *
 * @author wangcongjun
 * @date 2020/11/19 10:29
 */
public class BeanFactoryIocContainerDemo {
    public static void main(String[] args) {
        // 创建beanfactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String source = "META-INF/dependency-lookup-context.xml";
        int loadBeanDefinitions = reader.loadBeanDefinitions(source);
        System.out.println("bean 加载的数量"+ loadBeanDefinitions);
    }
}
