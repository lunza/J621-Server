package com.J621.dao;

import com.J621.vo.J621Image;
import com.J621.vo.J621ImageExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface J621ImageMapper {
    int countByExample(J621ImageExample example);

    int deleteByExample(J621ImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(J621Image record);

    int insertSelective(J621Image record);

    List<J621Image> selectByExample(J621ImageExample example);

    J621Image selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") J621Image record, @Param("example") J621ImageExample example);

    int updateByExample(@Param("record") J621Image record, @Param("example") J621ImageExample example);

    int updateByPrimaryKeySelective(J621Image record);

    int updateByPrimaryKey(J621Image record);
    @MapKey("url")
	Map<String, J621Image> getAllImagesByKey(String key);

	int getMxFileName(String key);
}