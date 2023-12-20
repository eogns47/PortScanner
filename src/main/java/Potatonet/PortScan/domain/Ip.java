package Potatonet.PortScan.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "IP", indexes = {
        @Index(name = "ip_crc_idx", columnList = "ip_crc")
})
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ip_id")
    private Long ipId;

    @Column(name = "ip", nullable = false, unique = true)
    private String ip;

    @Column(name = "ip_crc", nullable = false)
    private Long ipCrc;

    @Column(name = "create_time", nullable = false)

    private java.util.Date createTime;

    // 생성자, getter, setter 등 필요한 메서드들을 추가



    // getter, setter 등 필요한 메서드들을 추가
}

