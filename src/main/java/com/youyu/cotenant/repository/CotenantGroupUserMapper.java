package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantGroupUser;
import com.youyu.cotenant.entity.CotenantGroupUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantGroupUserMapper {
    long countByExample(CotenantGroupUserExample example);

    int deleteByExample(CotenantGroupUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantGroupUser record);

    int insertSelective(CotenantGroupUser record);

    List<CotenantGroupUser> selectByExample(CotenantGroupUserExample example);

    CotenantGroupUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantGroupUser record, @Param("example") CotenantGroupUserExample example);

    int updateByExample(@Param("record") CotenantGroupUser record, @Param("example") CotenantGroupUserExample example);

    int updateByPrimaryKeySelective(CotenantGroupUser record);

    int updateByPrimaryKey(CotenantGroupUser record);
}