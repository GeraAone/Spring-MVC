package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.UserServiceImpl;
import web.controller.HelloController;
import web.controller.UserController;

@Configuration
public class JavaConfig
{
    @Bean
    public UserServiceImpl userService() {
            return new UserServiceImpl();
    }

    @Bean
    public UserController userController()
    {
        return new UserController(userService());
    }
    @Bean
    public HelloController helloController()
    {
        return new HelloController();
    }

}
