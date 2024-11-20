package vietdung.ecom2_tvdung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ecom2TvdungApplication {

    public static void main(String[] args) {
        System.getProperties().put( "server.port", 8082 );
        SpringApplication.run(Ecom2TvdungApplication.class, args);
    }

}
