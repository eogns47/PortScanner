package Potatonet.PortScan.domain;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataPair {
    private List<Ip> ipList;
    private List<Integer> integerList;

    public DataPair(List<Ip> ipList, List<Integer> integerList) {
        this.ipList = ipList;
        this.integerList = integerList;
    }

    public List<Ip> getIpList() {
        return ipList;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }
}
