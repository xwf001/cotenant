package com.youyu.cotenant.repository;

import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.entity.CotenantUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CotenantUserMapper {
    long countByExample(CotenantUserExample example);

    int deleteByExample(CotenantUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CotenantUser record);

    int insertSelective(CotenantUser record);

    List<CotenantUser> selectByExample(CotenantUserExample example);

    CotenantUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CotenantUser record, @Param("example") CotenantUserExample example);

    int updateByExample(@Param("record") CotenantUser record, @Param("example") CotenantUserExample example);

    int updateByPrimaryKeySelective(CotenantUser record);

    int updateByPrimaryKey(CotenantUser record);
}