package com.rocky.newringtones.base.db.query;

import java.util.List;

/**
 * create by 2019/7/24
 * <p>
 * author: wgl
 * <p>
 * Believe in yourself, you can do it.
 */
public interface DbQuery<T> {
    boolean save(String tableName,T t,DbInterface dbInterface);
    String queryById(String tableName, String where, int id);
    List<T> queryAll(String tableName);
    boolean deleteById(String tableName,int deleteId);
    boolean deleteAll(String tableName,String sequenceName);
    void merge(String tabName,int mergeId,T t,DbInterface dbInterface);

}
