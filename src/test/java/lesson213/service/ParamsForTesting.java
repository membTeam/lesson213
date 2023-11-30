package lesson213.service;

import lesson213.models.Emploee;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ParamsForTesting {

    public static int department = 1;

    public static List<Emploee> lsEmploee = new ArrayList<>(
            List.of(
                    new Emploee("100", 613450000001L, "Николай", "Иванов", department, 50000),
                    new Emploee("101", 613450001001L, "Петр", "Иванов", department, 90000),
                    new Emploee("102", 613450002001L, "Александр", "Иванов", 2, 40000)
            )
    );

}
