import Potatonet.PortScan.application.ScanSaver;
import Potatonet.PortScan.domain.Ip;
import org.junit.jupiter.api.Test;


public class tester {
    @Test
    void testSaver(){
        ScanSaver scanSaver = new ScanSaver();
        Ip ip = new Ip();
        scanSaver.saveAllOpenPort(ip,80);

    }
}
