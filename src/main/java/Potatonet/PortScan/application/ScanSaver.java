package Potatonet.PortScan.application;

import Potatonet.PortScan.domain.Ip;
import Potatonet.PortScan.domain.Port;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ScanSaver extends EntityManagerFactoryMaker {



    public void saveAllOpenPort(Ip ip, int portNum) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        // JPA에서 모든 데이터 변경은 트랜잭션 내에서 실행한다.
        tx.begin();
        try {
            Timestamp currentTime = new Timestamp(new Date().getTime());
            Port port = Port.builder()
                    .ipId(ip.getIpId())
                    .port(portNum)
                    .alive(1)
                    .createTime(currentTime)
                    .updateTime(currentTime)
                    .build();

            // 생성된 Port 객체 저장
            em.persist(port);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        } finally {
            em.close();
        }

    }
}
