package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantCommunication;
import com.youyu.cotenant.entity.CotenantCommunicationExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantCommunicationMapper {
    long countByExample(CotenantCommunicationExample example);

    int deleteByExample(CotenantCommunicationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantCommunication record);

    int insertSelective(CotenantCommunication record);

    List<CotenantCommunication> selectByExample(CotenantCommunicationExample example);

    CotenantCommunication selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantCommunication record, @Param("example") CotenantCommunicationExample example);

    int updateByExample(@Param("record") CotenantCommunication record, @Param("example") CotenantCommunicationExample example);

    int updateByPrimaryKeySelective(CotenantCommunication record);

    int updateByPrimaryKey(CotenantCommunication record);
}