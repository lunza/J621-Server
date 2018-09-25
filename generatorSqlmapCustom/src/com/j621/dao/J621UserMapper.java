package com.J621.dao;

import com.J621.vo.J621User;
import com.J621.vo.J621UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface J621UserMapper {
    int countByExample(J621UserExample example);

    int deleteByExample(J621UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(J621User record);

    int insertSelective(J621User record);

    List<J621User> selectByExample(J621UserExample example);

    J621User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") J621User record, @Param("example") J621UserExample example);

    int updateByExample(@Param("record") J621User record, @Param("example") J621UserExample example);

    int updateByPrimaryKeySelective(J621User record);

    int updateByPrimaryKey(J621User record);
}