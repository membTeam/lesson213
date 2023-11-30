package lesson213.service;

import lesson213.exceptionAPI.ErrNotDataException;
import lesson213.models.Emploee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static lesson213.service.ParamsForTesting.department;
import static org.junit.jupiter.api.Assertions.*;

import lesson213.repositories.EmploeeRepositoryImpl;
import lesson213.services.ConfigLoadData;

public class EmploeeRepositoryTest {

    private EmploeeRepositoryImpl repo = new EmploeeRepositoryImpl();;

    @BeforeEach
    private void setUp() {
        var configLoadData = new ConfigLoadData(repo);
        configLoadData.run();
    }

    private Emploee firstEmploee() {
        return repo.getMap().values().stream().findFirst().orElseThrow();
    }

    @Test
    public void sizeRowData() {
        var sizeMap = repo.getMap().values().size();
        var size = repo.size();

        assertTrue(size > 0);
        assertEquals(sizeMap, size);
    }

    @Test
    public void existEmploeeWithAllParameters() {
        var exist = repo.existEmploee(613450000001L, "Даниил", "Андрианов");

        assertTrue(exist);
    }

    @Test
    public void findByINN() {
        var valINN = firstEmploee().getDataINN();

        var emploee = repo.findByINN(valINN).orElseThrow();

        assertNotNull(emploee);
        assertEquals(valINN, emploee.getDataINN());
    }

    @Test
    public void existEmploeeWithEmploee() {
        var emploee = firstEmploee();
        var exist = repo.existEmploee(emploee);

        assertTrue(exist);
    }

    @Test
    public void saveWithNotExistEmploee() {
        var valINN = 613450000010L;
        var emploee = new Emploee(null, valINN,
                "Даниил",
                "Андрианов",
                1, 10000 );

        var resSave = repo.save(emploee);

        var emploeeMap = repo.getMap().values().stream()
                .filter(emploee1 -> emploee1.getDataINN().equals(valINN))
                .findFirst().orElseThrow();

        assertTrue(resSave.isPresent());
        assertEquals(valINN, emploeeMap.getDataINN());
    }

    @Test
    public void saveWithNullParamsEmploee() {
        var emploee = new Emploee(null, 613450000001L,
                "Даниил",
                "Андрианов", null, null );

        assertThrows(ErrNotDataException.class, () -> repo.save(emploee));
    }

    @Test
    public void findAll() {
        var size = repo.getMap().values().size();

        var listSize = repo.findAll().orElseThrow().size();

        assertEquals(size, listSize);
    }

    @Test
    public void FindById() {

        var id = firstEmploee().getId();
        var resSave = repo.findById(id);

        assertTrue(resSave.isPresent());
    }

    @Test
    public void noFindById() {
        var resSave = repo.findById("100");
        assertTrue(resSave.isEmpty());
    }

    @Test
    public void maxSalary() {
        var max = repo.getMap().values().stream()
                .mapToInt(emploee -> emploee.getSalary())
                .max().orElseThrow();

        var maxSalary = repo.maxSalary();

        assertTrue(maxSalary>0);
        assertEquals(max, maxSalary);
    }

    @Test
    public void maxSalaryForDepartment() {
        var max = repo.getMap().values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .max().orElseThrow();

        var maxSalary = repo.maxSalary(department);

        assertTrue(maxSalary>0);
        assertEquals(max, maxSalary);
    }

    @Test
    public void minSalary() {
        var min = repo.getMap().values().stream()
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow();

        var minSalary = repo.minSalary();

        assertTrue(minSalary>0);
        assertEquals(min, minSalary);
    }

    @Test
    public void minSalaryForDepartment() {
        var department = 1;
        var min = repo.getMap().values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .min().orElseThrow();

        var minSalary = repo.minSalary(department);

        assertTrue(minSalary>0);
        assertEquals(min, minSalary);
    }

    @Test
    public void sumSalary() {
        var sum = repo.getMap().values().stream()
                .mapToInt(emploee -> emploee.getSalary())
                .sum();

        var sumSalary = repo.sumSalary();

        assertTrue(sumSalary>0);
        assertEquals(sum, sumSalary);
    }

    @Test
    public void sumSalaryForDepartment() {
        var department = 1;
        var sum = repo.getMap().values().stream()
                .filter(emploee -> emploee.getDepartment() == department)
                .mapToInt(emploee -> emploee.getSalary())
                .sum();

        var sumSalary = repo.sumSalary(department);

        assertTrue(sumSalary>0);
        assertEquals(sum, sumSalary);
    }

}
