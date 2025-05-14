package org.ame.yan.shabby;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.ame.yan.shabby.mapper") // 指定Mapper接口所在的包
public class ShabbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShabbyApplication.class, args);
    }

}
