package com.lz.applet;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class AppletApplicationTests {

    @Test
    void testLog(){
      log.error("log:error");
      log.warn("log:warn");
      log.info("log:info");
      log.debug("log:debug");

    }

}
