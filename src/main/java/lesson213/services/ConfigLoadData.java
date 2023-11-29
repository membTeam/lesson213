package lesson213.services;


import lesson213.Emploee;
import lesson213.repositories.EmploeeRepositoryImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class ConfigLoadData implements CommandLineRunner {

    private EmploeeRepositoryImpl repo;

    public ConfigLoadData(EmploeeRepositoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        try(var scanner = new Scanner(new File("extData/emploees.csv")) ) {
            repo.clearAll();

            while (scanner.hasNextLine()) {
                var arrString = scanner.nextLine().split(",") ;

                var emploee = new Emploee(null, arrString[0].trim(),
                        arrString[1].trim(), arrString[2].trim(),
                        Integer.parseInt(arrString[3].trim()),
                        Integer.parseInt(arrString[4].trim()));

                repo.save(emploee);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("err: " + ex.getMessage());
        }
    }

}
