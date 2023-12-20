package Potatonet.PortScan;
import static java.lang.Thread.sleep;

import Potatonet.PortScan.application.APICaller;
import Potatonet.PortScan.application.Scanner;
import Potatonet.PortScan.domain.DataPair;

import java.io.IOException;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main extends Thread{
    private static final Object lock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            Thread thread = new WorkerThread();
            thread.start();
        }
    }

    static class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Optional<DataPair> dataPair;
                synchronized (lock) {
                    APICaller apiCaller = new APICaller();
                    dataPair = apiCaller.getIpListWithPortList();
                }
                if (dataPair.isEmpty()) {
                    // sleep
                    try {
                        Thread.sleep(1000); // 1초 동안 스레드를 대기
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Scanner scanner = new Scanner();
                    scanner.scanAllIpPort(dataPair.get());
                }
            }
        }
    }
}