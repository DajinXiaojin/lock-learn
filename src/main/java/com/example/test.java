package com.example;

import com.alibaba.fastjson.JSON;
import com.example.demo.Person1;
import com.example.demo.Person2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

@Slf4j
public class test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString("1234".split("")));
        System.out.println("1234".split("")[0]);
        System.out.println("1234".split("")[2]);

        System.out.println("1234".toCharArray());
        System.out.println("1234".toCharArray()[1]);
        change();

        int[] array = new int[]{55, 33, 22, 66, 11};
        //输出排序前的array数组
        System.out.print("排序前：");
        System.out.println(Arrays.toString(array));
        //调用BubbleSort类中的sort方法对array数组进行排序
        try {
            log.info("长度{},{}",array.length,array);
            log.info("长度 ",array.length,array);
            sort(array);
            String g = null;
            boolean ee = g.equals("2");
        } catch (Exception e) {
            System.out.println("-----");
            e.printStackTrace();
            log.error("测试异常",e);
            System.out.println("=======");
            log.error("测试异常：{}",e);
            System.out.println("=======");
            log.error("测试异常：{}",e.toString());

        }
        //输出冒泡排序后的array数组
        System.out.print("排序后：");
        System.out.println(Arrays.toString(array));




    }


        public static void sort(int array[]) {
            //i表示第几轮“冒泡”，j 表示“走访”到的元素索引。
            // 每一轮“冒泡”中，j 需要从列表开头“走访”到 array.length - 1 的位置。
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - 1 - i; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }



    private static void change(){
        Person1 person1 = new Person1();
        person1.setAge("10");
        person1.setName("zhangsan");
        Person2 person2 = new Person2();

        BeanUtils.copyProperties(person1, person2);

        System.out.println(person2.getAge());


    }

}
