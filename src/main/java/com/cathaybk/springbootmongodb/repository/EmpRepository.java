package com.cathaybk.springbootmongodb.repository;

import com.cathaybk.springbootmongodb.model.EmpVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author RogerLo
 * @date 2022/6/15
 */
@Repository
public interface EmpRepository extends MongoRepository<EmpVO, String> {

    EmpVO findByEmpName(String eName);

    @Query("{ 'gender' : ?0 }")
    List<EmpVO> findEmpVOSByGender(String gender);

}
