package ga.yuan.yogo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        // 获取应用用户变量 server.port 值
        String serverPort = context.getEnvironment().getProperty("server.port");
        log.info("Yogo start at http://localhost:{}!", serverPort);
    }

}
