package cn.sf80.study.spring.environment;

import cn.sf80.study.spring.environment.entity.Address;
import cn.sf80.study.spring.ioc.overview.domain.User;
import cn.sf80.study.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * {@link PropertyPlaceholderConfigurer} 处理属性占位符示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
@ImportResource("classpath:/META-INF/placeholders-resolver.xml")
public class PropertyPlaceholderConfigurerDemo {

    @Value("${user.city}")
    @Lazy
    private Address address;

    public static void main(String[] args) {

        // 创建并且启动 Spring 应用上下文

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertyPlaceholderConfigurerDemo.class);
        context.refresh();
        User user = context.getBean("user", User.class);
        PropertyPlaceholderConfigurerDemo bean = context.getBean(PropertyPlaceholderConfigurerDemo.class);

        System.out.println(user);
        System.out.println(bean.address);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Bean(name = "conversionService")
    public ConversionServiceFactoryBean conversionService(){
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        HashSet<Object> converters = new HashSet<>();
        converters.add((new StringToEnumCityConverter()));
        converters.add((new StringToAddressConverter()));
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }

    public static class StringToEnumCityConverter implements Converter<String, City> {

        @Override
        public City convert(String source) {
            return City.BEIJING;
        }
    }

    public static class StringToAddressConverter implements ConditionalGenericConverter {
        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
            return String.class.equals(sourceType.getObjectType()) && Address.class.equals(targetType.getObjectType());
        }

        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(new ConvertiblePair(String.class, Address.class));
        }

        @Override
        public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
            Address address = new Address();
            address.setName((String)source);
            return address;
        }
    }
}
