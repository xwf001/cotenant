package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantReportedViolation;
import com.youyu.cotenant.entity.CotenantReportedViolationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantReportedViolationMapper {
    long countByExample(CotenantReportedViolationExample example);

    int deleteByExample(CotenantReportedViolationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantReportedViolation record);

    int insertSelective(CotenantReportedViolation record);

    List<CotenantReportedViolation> selectByExample(CotenantReportedViolationExample example);

    CotenantReportedViolation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantReportedViolation record, @Param("example") CotenantReportedViolationExample example);

    int updateByExample(@Param("record") CotenantReportedViolation record, @Param("example") CotenantReportedViolationExample example);

    int updateByPrimaryKeySelective(CotenantReportedViolation record);

    int updateByPrimaryKey(CotenantReportedViolation record);
}