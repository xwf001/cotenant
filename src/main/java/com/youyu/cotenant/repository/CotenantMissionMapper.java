package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantMission;
import com.youyu.cotenant.entity.CotenantMissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CotenantMissionMapper {
    long countByExample(CotenantMissionExample example);

    int deleteByExample(CotenantMissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantMission record);

    int insertSelective(CotenantMission record);

    List<CotenantMission> selectByExample(CotenantMissionExample example);

    CotenantMission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantMission record, @Param("example") CotenantMissionExample example);

    int updateByExample(@Param("record") CotenantMission record, @Param("example") CotenantMissionExample example);

    int updateByPrimaryKeySelective(CotenantMission record);

    int updateByPrimaryKey(CotenantMission record);
}