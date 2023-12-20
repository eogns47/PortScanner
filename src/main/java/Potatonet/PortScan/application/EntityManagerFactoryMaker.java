package Potatonet.PortScan.application;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryMaker {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

}
