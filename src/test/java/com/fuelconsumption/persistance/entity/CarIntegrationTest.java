package com.fuelconsumption.persistance.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fuelconsumption.persistance.exception.PersistenceServiceException;
import com.fuelconsumption.persistance.parameter.CarParameter;
import com.fuelconsumption.persistance.query.CarQuery;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarIntegrationTest {

    private static EntityManager em;
    private static EntityTransaction et;

    @BeforeClass
    public static void init1() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("integration");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }

    @Test
    public void test1PersistCars() {
        et.begin();
        em.persist(new Car("PLATE-1", "Opel", "Red"));
        em.persist(new Car("PLATE-2", "Audi", "White"));
        et.commit();
        final List<Car> actualList = em.createNamedQuery(CarQuery.GET_CARS, Car.class).getResultList();

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
    public void test2GetCarID() throws PersistenceServiceException {
        final Long carId = this.getCarId("PLATE-2");
        final Car car = em.find(Car.class, carId);
        assertThat(carId, is(2L));
        assertThat(car.getPlate(), is("PLATE-2"));
        assertThat(car.getType(), is("Audi"));
        assertThat(car.getColor(), is("White"));
    }

    @Test
    public void test3Merge() throws PersistenceServiceException {
        CarIntegrationTest.et.begin();
        final Long oldCarId = this.getCarId("PLATE-1");
        final Car newCar = em.find(Car.class, oldCarId);
        newCar.setColor("Black");
        CarIntegrationTest.em.merge(newCar);
        CarIntegrationTest.et.commit();
        final List<Car> actualList = em.createNamedQuery(CarQuery.GET_CARS, Car.class).getResultList();
        assertThat(actualList.get(0).getPlate(), is("PLATE-1"));
        assertThat(actualList.get(0).getType(), is("Opel"));
        assertThat(actualList.get(0).getColor(), is("Black"));
    }

    @Test
    public void test4Remove() throws PersistenceServiceException {
        et.begin();
        final Long oldCarId = this.getCarId("PLATE-1");
        final Car newCar = em.find(Car.class, oldCarId);
        newCar.setColor("Black");
        em.remove(newCar);
        et.commit();
        final List<Car> actualList = em.createNamedQuery(CarQuery.GET_CARS, Car.class).getResultList();
        assertThat(actualList.size(), is(1));
    }

    private Long getCarId(final String plate) throws PersistenceServiceException {
        Long result = null;
        final TypedQuery<Car> createNamedQuery = em.createNamedQuery(CarQuery.GET_CAR_BY_PLATE, Car.class);
        final TypedQuery<Car> setParameter = createNamedQuery.setParameter(CarParameter.PLATE, plate);
        final List<Car> list = setParameter.getResultList();
        if (!list.isEmpty()) {
            final Car car = list.get(0);
            result = car.getId();
        }
        return result;
    }

}
