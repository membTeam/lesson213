package lesson213.service;

import lesson213.models.Emploee;
import lesson213.repositories.EmploeeRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartmentControllerServImplTest {
    EmploeeRepositoryImpl emploeeRepo = new EmploeeRepositoryImpl();

    @BeforeEach
    private void setUp() {
        Map<String,Emploee> repo = emploeeRepo.getMap();
        repo.clear();

        List<Emploee> lsEmploee = List.of(
                new Emploee("100", 613450000001L, "Николай", "Иванов", 1, 50000),
                new Emploee("101", 613450001001L, "Петр", "Иванов", 1, 90000),
                new Emploee("102", 613450002001L, "Кирилл", "Иванов", 2, 60000),
                new Emploee("103", 613450003001L, "Василий", "Иванов", 2, 60000)
        );

        lsEmploee.stream().forEach(emploee -> repo.put(emploee.getId(), emploee));
    }

    @Test
    public void getSize() {
        var repo = emploeeRepo.getMap();
        assertTrue(repo.size() == 4);
    }

    @Test
    public void existEmploee() {
        var emploee = new Emploee("100", 613450000001L, "Николай", "Иванов", 1, 50000);

        var result = emploeeRepo.existEmploee(emploee);
        assertTrue(result);
    }

    @Test
    public void notExistEmploee() {
        var emploee = new Emploee("100", 613450000002L, "Николай", "Иванов", 1, 50000);

        var result = emploeeRepo.existEmploee(emploee);
        assertFalse(result);
    }

    @Test
    public void ExistEmploeeWithParams() {
        var emploee = new Emploee("100", 613450000001L, "Николай", "Иванов", 1, 50000);

        var result = emploeeRepo.existEmploee(emploee.getDataINN(), emploee.getFirstName(), emploee.getLastName());
        assertTrue(result);
    }

}
