package Potatonet.PortScan.application;

import Potatonet.PortScan.domain.DataPair;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import static Potatonet.PortScan.Config.Config.*;

public class APICaller {
    public Optional<DataPair> getIpListWithPortList() {
        try {
            String url = END_POINT; // 포트번호와 경로를 실제로 적절한 값으로 변경하세요.

            // URL 객체 생성
            URL obj = new URL(url);

            // HttpURLConnection 객체 생성
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // GET 메서드 설정
            con.setRequestMethod("GET");

            // 응답 코드 확인
            int responseCode = con.getResponseCode();
            System.out.println("HTTP 응답 코드: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // 성공적인 응답
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // JSON을 DataPair 객체로 변환
                Gson gson = new Gson();
                DataPair dataPair = gson.fromJson(response.toString(), DataPair.class);

                // DataPair 객체 반환
                return Optional.of(dataPair);
            } else {
                System.out.println("GET 요청 실패");
                return Optional.empty();
            }

        } catch (Exception e) {
            e.printStackTrace();

            return Optional.empty();
        }
    }
}