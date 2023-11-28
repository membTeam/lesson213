package lesson213.services;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ConfigLoadData implements CommandLineRunner {


    @Override
    public void run(String... args) {
        try {

        } catch (Exception ex) {
            System.out.println("err: " + ex.getMessage());
        }
    }

}
