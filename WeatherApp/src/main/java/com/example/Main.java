package com.example;


import com.example.entity.WeatherEntity;
import com.example.itil.WeatherApp;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/weather", new MyHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class MyHandler implements HttpHandler {
        private final Gson gson = new Gson();

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {

                URI requestUri = exchange.getRequestURI();
                String query = requestUri.getQuery();

                Map<String, String> params = parseQuery(query);
                Response responseObj = new Response();

                if (params.containsKey("cityName")) {
                    WeatherEntity weather = WeatherApp.getWeather(params.get("cityName"));
                    responseObj.setData(weather);
                } else {
                    responseObj.setMessage("Данные о погоде в указанном городе недоступны.");
                }
                String jsonResponse = gson.toJson(responseObj);
                sendJsonResponse(exchange, jsonResponse);
            } else {
                sendErrorResponse(exchange, "Неподдерживаемый метод: " + exchange.getRequestMethod());
            }
        }

        private Map<String, String> parseQuery(String query) {
            return Arrays.stream(query == null ? new String[0] : query.split("&"))
                    .map(param -> param.split("="))
                    .collect(Collectors.toMap(
                            pair -> URLDecoder.decode(pair[0], StandardCharsets.UTF_8),
                            pair -> pair.length > 1 ? URLDecoder.decode(pair[1], StandardCharsets.UTF_8) : ""
                    ));
        }

        private void sendJsonResponse(HttpExchange exchange, String jsonResponse) throws IOException {
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(200, jsonResponse.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
            os.close();
        }

        private void sendErrorResponse(HttpExchange exchange, String message) throws IOException {
            exchange.sendResponseHeaders(405, message.getBytes(StandardCharsets.UTF_8).length);
            OutputStream os = exchange.getResponseBody();
            os.write(message.getBytes(StandardCharsets.UTF_8));
            os.close();
        }

    }

    @Setter
    @Getter
    static class Response {
        private String message;
        private WeatherEntity data;

    }
}