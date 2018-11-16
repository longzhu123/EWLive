package org.ewlive.start;


import org.ewlive.listener.ApplicationStartedEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("org.ewlive")
public class EWLiveApplicationStart {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EWLiveApplicationStart.class);

        //添加应用启动监听类
        springApplication.addListeners(new ApplicationStartedEventListener());

        springApplication.run(args);

    }
}
