package com.example.mybatisplus;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class lambdaTest {

    public static void main(String[] args) {

        List<Person> personList = initData();
        // 2.1 从personList中筛选出年龄大于20的人
        List<Person> collect = personList.stream().filter(person -> person.getAge() < 20).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
        // 2.2 从personList中筛选出每个人的姓名
        List<String> nameList = personList.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(nameList));
        // 2.3 从personList中筛选出年龄大于20的每个人的姓名
        List<String> nameList2 = personList.stream().filter(person -> person.getAge() < 20).map(Person::getName).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(nameList2));
        // 2.4 把personList按照type分组
        Map<String, List<Person>> group1 = personList.stream().collect(Collectors.groupingBy(Person::getType));
        System.out.println(JSON.toJSONString(group1));
        // 2.4 把personList按照type分组并在集合中收集name字段
        Map<String, List<String>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getType, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(JSON.toJSONString(group2));
        // 2.5 把personList按照年龄分组并计算每个年龄段的总人数
        Map<Integer, Long> longMap = personList.stream().collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println(JSON.toJSONString(longMap));

       // 2.6 flatMap的使用(将List<Person>里面每个person中的所有list合并成一个list)，这里使用分组后的group1做示例
        List<String> list = personList.stream().map(Person::getList).flatMap(List::stream).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list));
        List<String> list2 = personList.stream().flatMap(person -> person.getList().stream()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list2));
    }

    // 初始化数据
    private static List<Person> initData() {
        List<Person> psersons = Lists.newArrayListWithCapacity(10);
        for (int i = 0; i < 4; i++) {
            Person person = new Person();
            person.setId("00" + (i + 1));
            person.setName("测试" + (i + 1));
            person.setType(i%2==0?"黑种人":"黄种人");
            person.setAge(10*(i+1));

            List<String> list = Lists.newArrayList("a"+i,"b"+i,"c"+i);
            person.setList(list);
            psersons.add(person);
        }
        return psersons;
    }

}

@Data
class Person {
    private String id;
    private String name;
    private String type;
    private int age;
    private List<String> list;
}
