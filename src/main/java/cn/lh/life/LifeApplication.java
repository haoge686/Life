package cn.lh.life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author life.lh.cn
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LifeApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(LifeApplication.class, args);
        System.out.println("美好的切又重新开始了~\n");
    }
}