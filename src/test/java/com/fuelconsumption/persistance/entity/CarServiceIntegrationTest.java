package com.fuelconsumption.persistance.entity;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fuelconsumption.persistance.entity.Car;
import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.manager.CarServiceManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarServiceIntegrationTest {

    private static EntityManager entityManager;
    private static EntityTransaction entityTransaction;

    private static CarServiceManager cut;

    @BeforeClass
    public static void init() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("integration");
        entityManager = emf.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        cut = new CarServiceManager(entityManager);
    }

    @Test
    public void test1AddCars() throws PersistenceServiceException {
        entityTransaction.begin();
        cut.addCar("PLATE-1", "Opel", "Red");
        cut.addCar("PLATE-2", "Audi", "White");
        entityTransaction.commit();
        final List<Car> actualList = cut.getCars();

        assertThat(actualList.size(), is(2));
        assertThat(actualList.get(0).getPlate(), is("PLATE-1"));
        assertThat(actualList.get(0).getType(), is("Opel"));
        assertThat(actualList.get(0).getType(), not("OPEL"));
        assertThat(actualList.get(0).getColor(), is("Red"));

        assertThat(actualList.get(1).getPlate(), is("PLATE-2"));
        assertThat(actualList.get(1).getType(), is("Audi"));
        assertThat(actualList.get(1).getColor(), is("White"));
    }

    @Test
    public void test2ModifyCar() throws PersistenceServiceException {
        entityTransaction.begin();
        cut.modifyCar("PLATE-1", "PLATE-3");
        cut.modifyCar("PLATE-2", "PLATE-4");
        entityTransaction.commit();
        final List<Car> actualList = cut.getCars();

        assertThat(actualList.size(), is(2));
        assertThat(actualList.get(0).getPlate(), is("PLATE-3"));
        assertThat(actualList.get(0).getType(), is("Opel"));
        assertThat(actualList.get(0).getType(), not("OPEL"));
        assertThat(actualList.get(0).getColor(), is("Red"));

        assertThat(actualList.get(1).getPlate(), is("PLATE-4"));
        assertThat(actualList.get(1).getType(), is("Audi"));
        assertThat(actualList.get(1).getColor(), is("White"));

    }

    @Test
    public void test3DeleteCar() throws PersistenceServiceException {
        entityTransaction.begin();
        cut.deleteCar("PLATE-3");
        cut.deleteCar("PLATE-4");
        entityTransaction.commit();

        assertThat(cut.getCars().isEmpty(), is(true));

    }

}
