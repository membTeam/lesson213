package lesson213.service;

import lesson213.repositories.EmploeeRepositoryImpl;
import lesson213.web.DepartmentControllerServImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class DepartmentControllerServImplTest {

    @Mock
    EmploeeRepositoryImpl repo;

    @InjectMocks
    DepartmentControllerServImpl servImpl;

}
