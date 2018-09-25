package com.j621.dao;

import com.j621.vo.J621Image;
import com.j621.vo.J621ImageExample;
import com.j621.vo.J621ImageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface J621ImageMapper {
    int countByExample(J621ImageExample example);

    int deleteByExample(J621ImageExample example);

    int deleteByPrimaryKey(J621ImageKey key);

    int insert(J621Image record);

    int insertSelective(J621Image record);

    List<J621Image> selectByExample(J621ImageExample example);

    J621Image selectByPrimaryKey(J621ImageKey key);

    int updateByExampleSelective(@Param("record") J621Image record, @Param("example") J621ImageExample example);

    int updateByExample(@Param("record") J621Image record, @Param("example") J621ImageExample example);

    int updateByPrimaryKeySelective(J621Image record);

    int updateByPrimaryKey(J621Image record);
}