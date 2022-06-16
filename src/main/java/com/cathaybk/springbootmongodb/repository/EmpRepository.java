package com.cathaybk.springbootmongodb.repository;

import com.cathaybk.springbootmongodb.model.EmpVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author RogerLo
 * @date 2022/6/15
 */
@Repository
public interface EmpRepository extends MongoRepository<EmpVO, String> {

    public EmpVO findByEmpName(String eName);

}
