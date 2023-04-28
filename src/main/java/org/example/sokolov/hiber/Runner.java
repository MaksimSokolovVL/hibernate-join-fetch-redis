package org.example.sokolov.hiber;

import org.example.sokolov.hiber.config.AppConfig;
import org.example.sokolov.hiber.domain.entity.City;
import org.example.sokolov.hiber.redis.CityCountry;
import org.example.sokolov.hiber.service.CityService;
import org.example.sokolov.hiber.util.RedisUtil;
import org.example.sokolov.hiber.util.TransformDataUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        RedisUtil redis = new RedisUtil();

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CityService cityService = context.getBean(CityService.class);

/*        CountryService countryService = context.getBean(CountryService.class);
        List<Country> countries = countryService.getAll();
        countries.forEach(System.out::println);
        System.out.println(cityService.getTotalCount());
        List<City> cityList = cityService.getItems(0, 5);
        cityList.forEach(System.out::println);*/

//----------------------------------------------------------------//
        List<City> allCityByStep = cityService.getAllCityByStep(500);
//        allCityByStep.forEach(System.out::println);
//
        List<CityCountry> preparedData = TransformDataUtil.transformData(allCityByStep);

        redis.pushToRedis(preparedData);

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long stop, start;

        start = System.currentTimeMillis();
        redis.testRedisData(ids);
        stop = System.currentTimeMillis();
        System.out.printf("%s:\t%d ms\n", "Redis", (stop - start));

        start = System.currentTimeMillis();
        cityService.testMysqlData(ids);
        stop = System.currentTimeMillis();
        System.out.printf("%s:\t%d ms\n", "MySQL", (stop - start));
    }
}