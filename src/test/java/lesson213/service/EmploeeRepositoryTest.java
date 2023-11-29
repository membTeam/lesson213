package lesson213.service;

import lesson213.models.Emploee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lesson213.repositories.EmploeeRepositoryImpl;
import lesson213.services.ConfigLoadData;

public class EmploeeRepositoryTest {

    EmploeeRepositoryImpl repo;

    @BeforeEach
    private void setUp() {
        repo =  new EmploeeRepositoryImpl();
        var configLoadData = new ConfigLoadData(repo);
        configLoadData.run();
    }

    @Test
    public void sizeRowData() {
        assertTrue(repo.size()>0);
    }

    @Test
    public void existEmploeeWithAllParameters() {
        var exist = repo.existEmploee(613450000001L, "Даниил", "Андрианов");

        assertTrue(exist);
    }

    @Test
    public void existEmploeeWithEmploee() {
        var emploee = new Emploee(null, 613450000001L, "Даниил", "Андрианов", null, null );
        var exist = repo.existEmploee(emploee);

        assertTrue(exist);
    }

    @Test
    public void saveWithNotExistEmploee() {
        var emploee = new Emploee(null, 613450000010L, "Даниил", "Андрианов", 1, 10000 );

        var resSave = repo.save(emploee);
        assertTrue(resSave.isPresent());
    }

    @Test
    public void saveWithNullParamsEmploee() {
        var emploee = new Emploee(null, 613450000001L, "Даниил", "Андрианов", null, null );

        var resSave = repo.save(emploee);
        assertTrue(resSave.isEmpty());
    }

    @Test
    public void findAll() {
        var listEmploee = repo.findAll();
        assertTrue(listEmploee.isPresent());
    }

    @Test
    public void FindById() {

        var emploee = repo.findAll().orElseThrow().get(0);

        var resSave = repo.findById(emploee.getId());
        assertTrue(resSave.isPresent());
    }

    @Test
    public void noFindById() {
        var resSave = repo.findById("100");
        assertTrue(resSave.isEmpty());
    }

    @Test
    public void maxSalary() {
        var maxSalary = repo.maxSalary();
        assertTrue(maxSalary>0);
    }

    @Test
    public void maxSalaryForDepartment() {
        var maxSalary = repo.maxSalary(1);
        assertTrue(maxSalary>0);
    }

    @Test
    public void minSalary() {
        var minSalary = repo.minSalary();
        assertTrue(minSalary>0);
    }

    @Test
    public void minSalaryForDepartment() {
        var minSalary = repo.minSalary(1);
        assertTrue(minSalary>0);
    }

    @Test
    public void sumSalary() {
        var sumSalary = repo.sumSalary();
        assertTrue(sumSalary>0);
    }

    @Test
    public void sumSalaryForDepartment() {
        var sumSalary = repo.sumSalary(1);
        assertTrue(sumSalary>0);
    }

}
