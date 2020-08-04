package com.lz.applet;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.lz.applet.mapper")
@Import(FdfsClientConfig.class)
public class AppletApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppletApplication.class, args);
    }

}
