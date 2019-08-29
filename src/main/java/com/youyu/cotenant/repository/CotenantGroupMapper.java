package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantGroup;
import com.youyu.cotenant.entity.CotenantGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantGroupMapper {
    long countByExample(CotenantGroupExample example);

    int deleteByExample(CotenantGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantGroup record);

    int insertSelective(CotenantGroup record);

    List<CotenantGroup> selectByExample(CotenantGroupExample example);

    CotenantGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantGroup record, @Param("example") CotenantGroupExample example);

    int updateByExample(@Param("record") CotenantGroup record, @Param("example") CotenantGroupExample example);

    int updateByPrimaryKeySelective(CotenantGroup record);

    int updateByPrimaryKey(CotenantGroup record);
}