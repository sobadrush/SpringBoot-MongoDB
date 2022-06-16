package com.cathaybk.springbootmongodb.repository;

import com.cathaybk.springbootmongodb.SpringBootMongoDbApplication;
import com.cathaybk.springbootmongodb.model.EmpVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * @author RogerLo
 * @date 2022/6/15
 */
@SpringBootTest(classes = { SpringBootMongoDbApplication.class })
public class EmpRepository_Test {

    @Autowired
    private EmpRepository empRepository;

    @BeforeEach
    void execBeforeEach(TestInfo testInfo) {
        System.err.println("========================== " + testInfo.getDisplayName() + " ==========================");
    }

    @Test
    @Disabled
    void test001() {
        System.err.println("empRepository = " + empRepository);
        Assertions.assertNotNull(empRepository);
    }

    @Test
    @Disabled
    void test002() {
        System.err.println("empRepository.findAll() = ");
        empRepository.findAll().stream().forEach(vo -> {
            System.out.println("vo = " + vo);
        });
        System.out.println("====================================================");
        System.out.println("empRepository.findByEmpName(\"Roger\") = ");
        System.out.println("vo = " + empRepository.findByEmpName("Roger"));
    }

    @Test
    @Disabled
    void test003() {
        EmpVO insertedVO = empRepository.insert(
                EmpVO.builder()
                        .empName("Snow")
                        .empSalary(new BigDecimal(42000))
                        .empTitle("總司令")
                        .gender("M")
                        .build());
        System.out.println("insertedVO = " + insertedVO);
    }

    @Test
    @Disabled
    void test004() {
        empRepository.findEmpVOSByGender("M")
                .stream()
                .forEach(System.out::println);
    }

}
