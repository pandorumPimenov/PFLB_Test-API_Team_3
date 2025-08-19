package jdbc;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface DbConfig extends Config {

    @Key("dbUrl")
    String dbUrl();

    @Key("dbUser")
    String dbUser();

    @Key("dbPassword")
    String dbPassword();
}
