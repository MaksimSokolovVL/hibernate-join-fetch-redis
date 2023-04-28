package org.example.sokolov.hiber.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisStringCommands;
import org.example.sokolov.hiber.redis.CityCountry;

import java.util.List;

public class RedisUtil {

    private static RedisClient prepareRedisClient() {
        return RedisClient.create(RedisURI.create("80.78.246.73", 6379));
    }

    public void pushToRedis(List<CityCountry> data) {
        ObjectMapper mapper = new ObjectMapper();
        try (RedisClient redisClient = prepareRedisClient();
             StatefulRedisConnection<String, String> connection = redisClient.connect()
        ) {
            RedisStringCommands<String, String> sync = connection.sync();

            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void testRedisData(List<Integer> ids) {
        ObjectMapper mapper = new ObjectMapper();
        try (RedisClient redisClient = prepareRedisClient();
             StatefulRedisConnection<String, String> connection = redisClient.connect()
        ) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                String value = sync.get(String.valueOf(id));
                try {
                    mapper.readValue(value, CityCountry.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

