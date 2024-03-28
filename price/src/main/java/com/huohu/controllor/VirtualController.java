package com.huohu.controllor;

import java.net.HttpURLConnection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("Virtual")
public class VirtualController {
    private static final String BASE_URL = "https://www.mingjianyiren.com/api/v5/";
    /*
      获取所有虚拟币行情
      24h涨幅计算方式
      涨幅的计算公式为:涨幅=(现价-上一个交易日收盘价)/上一个交易日收盘价*100%。
     */
    @RequestMapping("findPrice")
    public ResponseEntity<Object> findPrice() {
        // 使用配置化的请求URL和请求方式
        String url = "https://www.mingjianyiren.com/api/v5/public/instruments?instType=MARGIN";
        // 优化异常处理和资源管理
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setRequestMethod("GET");
            // 检查HTTP请求是否成功
            if (connection.getResponseCode() != 200) {
                // 处理非200响应码的情况，抛出异常
                throw new IOException("HTTP GET Request Failed with Error code: " + connection.getResponseCode() + " for URL: " + url);
            }
            // 读取并处理响应数据
            StringBuilder result = new StringBuilder();
            try (BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String output;
                while ((output = responseBuffer.readLine()) != null) {
                    result.append(output);
                }
            }
            // 解析JSON响应
            JSONObject jsonResponse = new JSONObject(result.toString());
            // 将解析后的JSON响应返回给前端
            return ResponseEntity.ok().body(jsonResponse.toString());

        } catch (IOException e) {
            // 更细致的异常处理，可以考虑将错误信息记录到日志系统
            // 此处为简化直接打印堆栈跟踪
            // 返回一个友好的错误消息给前端，而不是抛出异常
            return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST).body("{\"error\":\"Data retrieval failed.\"}");
        }
    }
    @RequestMapping("findIncrease")
    public ResponseEntity<Object> findIncrease() {
        // 使用配置化的请求URL和请求方式
        String url = "https://data.mifengcha.com/api/v3/price?api_key=HZXEVFYHWT1Y0CQCKIS7ZV14ZABIVWLXGETH8DB8";
        // 优化异常处理和资源管理
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
            connection.setRequestMethod("GET");
            // 检查HTTP请求是否成功
            if (connection.getResponseCode() != 200) {
                // 处理非200响应码的情况，抛出异常
                throw new IOException("HTTP GET Request Failed with Error code: " + connection.getResponseCode() + " for URL: " + url);
            }
            // 读取并处理响应数据
            StringBuilder result = new StringBuilder();
            try (BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String output;
                while ((output = responseBuffer.readLine()) != null) {
                    result.append(output);
                }
            }
            // 解析JSON响应
            JSONObject jsonResponse = new JSONObject(result.toString());
            // 将解析后的JSON响应返回给前端
            return ResponseEntity.ok().body(jsonResponse.toString());

        } catch (IOException e) {
            // 更细致的异常处理，可以考虑将错误信息记录到日志系统
            // 此处为简化直接打印堆栈跟踪
            // 返回一个友好的错误消息给前端，而不是抛出异常
            return ResponseEntity.status(HttpURLConnection.HTTP_BAD_REQUEST).body("{\"error\":\"Data retrieval failed.\"}");
        }
    }
    /*
      获取指定虚拟币行情
      instId: 币对名称
      url: https://www.mingjianyiren.com/api/v5/market/index-tickers?instId=btc-usdt
     */
    @RequestMapping(value = "findByIdPrice", method = RequestMethod.GET)
    public ResponseEntity<Object> findByIdPrice(@RequestParam String instId) throws IOException {
        // 对instId进行编码，防止URL注入
        String encodedInstId = URLEncoder.encode(instId, String.valueOf(StandardCharsets.UTF_8));
        // 请求路径
        String url = "https://www.mingjianyiren.com/api/v5/market/index-tickers?instId=" + encodedInstId;
        URL serverUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
        // 请求方式
        connection.setRequestMethod("GET");

        try {
            // 判断是否成功
            if (connection.getResponseCode() != 200) {
                // 返回更详细的错误信息
                String errorMessage = "HTTP GET Request Failed with Error code: " + connection.getResponseCode();
                return ResponseEntity.status(HttpStatus.valueOf(connection.getResponseCode())).body(errorMessage);
            }

            try (BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder result = new StringBuilder(1024); // 预估容量
                String output;
                while ((output = responseBuffer.readLine()) != null) {
                    result.append(output);
                }
                // 将结果转换为JSONObject或JSONArray（根据API实际返回的数据结构）
                JSONObject jsonResponse = new JSONObject(result.toString());
                // 返回给前端
                return ResponseEntity.ok().body(jsonResponse.toString());
            } finally {
                // 关闭HTTP连接
                connection.disconnect();
            }
        } catch (IOException e) {
            // 捕获异常，返回500错误
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: "+ e.getMessage());
        }
    }
    /*
    获取指定虚拟币行情K线
    instId: 币对名称
    url: https://www.mingjianyiren.com/api/v5/market/index-candles?instId=btc-usdt
    返回值：json字符串
   */
    @RequestMapping("findKLine")
    public ResponseEntity<Object> findKLine(@RequestParam String instId) throws IOException {
        // 参数编码，防止URL注入
        String encodedInstId = URLEncoder.encode(instId, StandardCharsets.UTF_8.toString());
        String url = "https://www.mingjianyiren.com/api/v5/market/index-candles?instId=" + encodedInstId;

        URL serverUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) serverUrl.openConnection();
        try {
            connection.setRequestMethod("GET");
            //判断http是否错误
            if (connection.getResponseCode() != 200) {
                // 更详细的错误处理
                throw new IOException("HTTP GET Request Failed with Error code: " + connection.getResponseCode());
            }

            try (BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder result = new StringBuilder();
                String output;
                while ((output = responseBuffer.readLine()) != null) {
                    result.append(output);
                }

                JSONObject config = new JSONObject(result.toString());
                // 返回给前端
                return ResponseEntity.ok().body(config.toString());
            } finally {
                // 关闭连接
                connection.disconnect();
            }
        } catch (IOException e) {
            // 更好的异常处理，可记录日志或转换为更合适的异常类型抛出
            throw new IOException("Error occurred while connecting to the server or reading the response.",e);
        }
    }

    /*
    获取虚拟货币的总持仓量
    url: https://www.mingjianyiren.com/api/v5/public/open-interest?instType=SWAP
    instType：产品类型
            : SWAP：   永续合约
            : FUTURES：交割合约
            : OPTION： 期权
    返回值：json字符串
    */
    @RequestMapping("findPosition")
    public ResponseEntity<String> findPosition(@RequestParam String instType) {
        String url = BASE_URL + "public/open-interest?instType=" + encodeParam(instType);
        try {
            JSONObject jsonResponse = makeGetRequest(url);
            return ResponseEntity.status(HttpStatus.OK).body(jsonResponse.toString());
        } catch (IOException e) {
            // 适当地处理IO异常，例如记录日志或返回一个特定的错误响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Internal Server Error\"}");
        } catch (Exception e) {
            // 处理其他可能的异常，例如非法参数或网络问题

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\":\"Bad Request\"}");
        }
    }

    private JSONObject makeGetRequest(String url) throws IOException, Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept-Charset", StandardCharsets.UTF_8.name());

        if (connection.getResponseCode() != 200) {
            throw new Exception("HTTP GET Request Failed with Error code: " + connection.getResponseCode());
        }

        try (BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder result = new StringBuilder();
            String output;
            while ((output = responseBuffer.readLine()) != null) {
                result.append(output);
            }
            return new JSONObject(result.toString());
        } finally {
            connection.disconnect();
        }
    }

    private String encodeParam(String param) {
        try {
            return java.net.URLEncoder.encode(param, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            // 处理编码异常
            System.err.println("Error encoding parameter: " + param);
            return param;
        }
    }
}
