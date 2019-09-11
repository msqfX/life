package com.dlion.life.common.data;

/**
 * @param <T>  实体泛型
 * @param <ID> 实体主键泛型
 * @author 李正元
 * @date 2019/9/11
 */
public interface BaseMapper<T, ID> {

    Integer insertSelective(T t);

    T selectByPrimaryKey(ID id);

    Integer updateByPrimaryKeySelective(T t);

    Integer deleteByPrimaryKey(ID id);

    Integer insert(ID id);

    Integer updateByPrimaryKey(T t);
}
