package lesson213.service;

import lesson213.models.Emploee;
import lesson213.repositories.EmploeeRepositoryImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmploeeRepositoryWithMockTest {

    private final EmploeeRepositoryImpl repoMock = mock(EmploeeRepositoryImpl.class);

    private static Emploee emploeeExists = new Emploee("100", 613450000001L, "Даниил", "Андрианов", null, null);
    private static Emploee emploeeNotExists = new Emploee(null, 613450000011L, "Даниил", "Андрианов", 1, 50000 );

    @Test
    public void sizeRowData() {
        when(repoMock.size()).thenReturn(8);

        assertTrue(repoMock.size()>0);
    }

    @Test
    public void existEmploeeWithEmploee() {
        when(repoMock.existEmploee(emploeeExists)).thenReturn(true);

        var exist = repoMock.existEmploee(emploeeExists);

        assertTrue(exist);
    }

    @Test
    public void saveWithNotExistEmploee() {

        when(repoMock.existEmploee(emploeeNotExists)).thenReturn(false);
        when(repoMock.save(emploeeNotExists)).thenReturn(Optional.of(emploeeNotExists));

        var resSave = repoMock.save(emploeeNotExists);
        assertTrue(resSave.isPresent());
    }

    @Test
    public void saveWithNullParamsEmploee() {
        var emploee = new Emploee(null, 613450000001L, "Даниил", "Андрианов", null, null );

        when(repoMock.existEmploee(emploee)).thenReturn(false);
        when(repoMock.save(emploee)).thenReturn(Optional.empty());

        var resSave = repoMock.save(emploee);

        assertTrue(resSave.isEmpty());
    }

    @Test
    public void findAll() {
        List<Emploee> lsEmploee = List.of(new Emploee(null, 100L, "","",null, null));

        when(repoMock.findAll()).thenReturn(Optional.of(lsEmploee));

        var listEmploee = repoMock.findAll();
        assertTrue(listEmploee.isPresent());
    }

    @Test
    public void FindById() {
        when(repoMock.save(emploeeExists)).thenReturn(Optional.of(emploeeExists));
        when(repoMock.findById(emploeeExists.getId())).thenReturn(Optional.of(emploeeExists));

        var resSave = repoMock.findById(emploeeExists.getId());
        assertTrue(resSave.isPresent());
    }

    @Test
    public void notFindById(){
        Emploee emploee = emploeeExists;
        emploee.setId("110");

        when(repoMock.findById(emploee.getId())).thenReturn(Optional.empty());
        var result = repoMock.findById(emploee.getId());

        assertFalse(result.isPresent());
    }

}
