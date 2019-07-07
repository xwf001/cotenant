package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantMissionUser;
import com.youyu.cotenant.entity.CotenantMissionUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CotenantMissionUserMapper {
    long countByExample(CotenantMissionUserExample example);

    int deleteByExample(CotenantMissionUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantMissionUser record);

    int insertSelective(CotenantMissionUser record);

    List<CotenantMissionUser> selectByExample(CotenantMissionUserExample example);

    CotenantMissionUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantMissionUser record, @Param("example") CotenantMissionUserExample example);

    int updateByExample(@Param("record") CotenantMissionUser record, @Param("example") CotenantMissionUserExample example);

    int updateByPrimaryKeySelective(CotenantMissionUser record);

    int updateByPrimaryKey(CotenantMissionUser record);
}