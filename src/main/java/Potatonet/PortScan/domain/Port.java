package Potatonet.PortScan.domain;

import java.sql.Timestamp;
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
@Builder
@AllArgsConstructor
@Table(name = "Port", indexes = {
        @Index(name = "idx_ipId", columnList = "ipId"),
        @Index(name = "idx_port", columnList = "port")
})
@NoArgsConstructor
public class Port {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portId;

    @Column(nullable = false)
    private Long ipId;

    @Column(nullable = false)
    private int port;

    private int alive;

    private Timestamp createTime;

    private Timestamp updateTime;

}
