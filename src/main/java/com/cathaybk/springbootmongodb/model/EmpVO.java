package com.cathaybk.springbootmongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;

/**
 * @author RogerLo
 * @date 2022/6/15
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "emps")
public class EmpVO {

    @MongoId // 使用 @MongoID 能更清晰的指定 _id 主键
    // @Field(value = "_id")
    private String empId; // 形式：62a9f72ecd316324ae712142

    @Field(value = "empName")
    private String empName;

    @Field(value = "empTitle")
    private String empTitle;

    @Field(value = "empSalary")
    private BigDecimal empSalary;

    @Field(value = "gender")
    private String gender;

}
