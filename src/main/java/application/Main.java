package application;

import domain.Employee;
import domain.PersonEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        PersonEntity personEntity = makeTestPerson();
        Employee employee = makeEmployee();

        try {
            transaction.begin();

            entityManager.persist(employee);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return;
        }

        EntityTransaction readTransaction = entityManager.getTransaction();
        readTransaction.begin();

        Employee newAddedEmployee = entityManager.find(Employee.class, employee.getId());
        if (newAddedEmployee != null) {
            System.out.println("newAddedPerson: " + newAddedEmployee);
        } else {
            System.out.println("cannot find newAddedPerson");
        }

        entityManager.close();
        entityManagerFactory.close();

        readTransaction.commit();
    }

    public static PersonEntity makeTestPerson() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(RandomStringUtils.randomAlphabetic(5));
        personEntity.setAge(RandomUtils.nextInt(10, 100));
        personEntity.setContent("content" + RandomStringUtils.random(5));
        return personEntity;
    }

    public static Employee makeEmployee() {
        Employee employee = new Employee();
        employee.setFirstName(RandomStringUtils.randomAlphabetic(5));
        employee.setLastName(RandomStringUtils.randomAlphabetic(5));
        employee.setSalary(RandomUtils.nextInt(0, 20));
        employee.setYearsOfService(RandomUtils.nextInt(0, 20));
        employee.setAddress(RandomUtils.nextInt(0, 100));

        return employee;
    }
}
