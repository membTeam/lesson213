package lesson213.service;

import lesson213.repositories.EmploeeRepositoryImpl;
import lesson213.services.ConfigLoadData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoadFromFileTest {

    @Test
    public void ConfigLoadDataRun() {
        var repo = new EmploeeRepositoryImpl();
        var configLoadData = new ConfigLoadData(repo);

        configLoadData.run();

        assertTrue(repo.size()>0);
    }

}
