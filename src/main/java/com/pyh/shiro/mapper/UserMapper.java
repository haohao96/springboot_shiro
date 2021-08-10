package com.pyh.shiro.mapper;

import com.pyh.shiro.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user")
    public List<User> select();

    @Select("select * from user where name=#{name}")
    public User selectUserByName(String name);

    @Insert("insert into user values(#{user.id},#{user.name},#{user.password},#{user.perms})")
    public void add(@Param("user")User user);

    @Update("update user set name=#{new_name} where id=#{id}")
    public void update(@Param("new_name")String new_name,@Param("id")Integer id);

    @Delete("delete from user where id=#{id}")
    public void delete(@Param("id")Integer id);

}
