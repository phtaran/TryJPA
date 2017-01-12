package application;

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

        try {
            transaction.begin();

            entityManager.persist(personEntity);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return;
        }

        EntityTransaction readTransaction = entityManager.getTransaction();
        readTransaction.begin();

        PersonEntity newAddedPerson = entityManager.find(PersonEntity.class, personEntity.getId());
        if (newAddedPerson != null) {
            System.out.println("newAddedPerson: " + newAddedPerson);
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
}
