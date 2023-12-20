package Potatonet.PortScan.application;

import static Potatonet.PortScan.Config.Config.*;

import Potatonet.PortScan.domain.DataPair;
import Potatonet.PortScan.domain.Ip;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Scanner {

    ScanSaver scanSaver = new ScanSaver();
    public void scanAllIpPort(DataPair dataPair) {
        List<Ip> AllIp = dataPair.getIpList();
        List<Integer> portList = dataPair.getIntegerList();
        System.out.println("스캔할 IP 범위= "+ AllIp.get(0).getIpId() + " ~ " + AllIp.get(AllIp.size()-1).getIpId());
        System.out.println("스캔할 포트 목록:"+portList);
        long startTime = System.currentTimeMillis();
        int openNum=0;
        for (Ip ip : AllIp) {
            for (int portNum : portList) {
                //모든 IP에 대해 포트현황을 받아와서 데이터베이스에 저장
                int status = scanPortStatus(ip, portNum);
                if (status==SUCCESS_CODE) {
                    //저장
                    openNum++;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("범위 "+ AllIp.get(0).getIpId() + " ~ " + AllIp.get(AllIp.size()-1).getIpId()+ "에 포함된 Open Port 개수: "+ openNum);
        System.out.println("Processing time: " + (endTime - startTime)/1000 + " seconds");

    }

    public int scanPortStatus(Ip ip, int port) {
        int timeout = 1000; // 타임아웃 설정 (1초)

        // 예시로 포트 80을 스캔하는 코드
        int portNumber = port;

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip.getIp(), portNumber), timeout);
            // 포트가 열려있는 경우 처리 로직
            //System.out.println("IP: " + ip.getIp() + ", Port " + portNumber + " is open.");
            scanSaver.saveAllOpenPort(ip,portNumber);
            return SUCCESS_CODE;

        } catch (Exception e) {
            // 포트가 닫혀있거나 접속에 실패한 경우 처리 로직
            //System.out.println("IP: " + ip.getIp() + ", Port " + portNumber + " is closed.");
            return ERROR_CODE;
        }
    }


}
