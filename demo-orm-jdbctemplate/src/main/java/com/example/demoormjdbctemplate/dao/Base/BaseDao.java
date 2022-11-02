package com.example.demoormjdbctemplate.dao.Base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demoormjdbctemplate.annotation.Column;
import com.example.demoormjdbctemplate.annotation.Ignore;
import com.example.demoormjdbctemplate.annotation.Pk;
import com.example.demoormjdbctemplate.annotation.Table;
import com.example.demoormjdbctemplate.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
public class BaseDao<T, P> {
    private JdbcTemplate jdbcTemplate;
    private Class<T> clazz;

    public BaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        log.debug(clazz.getTypeName());
    }


    /***
     *
     * @param t      表
     * @param ignoreNull  是否忽略为null的字段
     * @return 返回字段列表
     * */
    private List<Field> getField(T t, Boolean ignoreNull) {
//        得到全表的字段
        Field[] fields = ReflectUtil.getFields(t.getClass());

//       过滤后的字段
        List<Field> filterField;
//        实现过滤操作(得到非空字段和非自增字段)
        Stream<Field> filterStream = CollUtil.toList(fields).stream().
                filter(field -> ObjectUtil.isNull(field.getAnnotation(Ignore.class)) || ObjectUtil.isNull(field.getAnnotation(Pk.class)));

//       是否过滤Null字段
        if (ignoreNull) {
            filterField = filterStream.filter(field -> ObjectUtil.isNotNull(ReflectUtil.getFieldValue(t, field))).collect(Collectors.toList());
        } else {
            filterField = filterStream.collect(Collectors.toList());
        }
        return filterField;
    }


    /***
     *
     * @param fieldList 字段列表
     * @return 列信息列表
     */
    private List<String> getColumns(List<Field> fieldList) {
        List<String> columnList = CollUtil.newArrayList();
        for (Field field : fieldList) {
            System.out.println(field);
            Column columnAnnotation = field.getAnnotation(Column.class);
            String columnName;
            if (ObjectUtil.isNotNull(columnAnnotation)) {
                columnName = columnAnnotation.name();
                System.out.println(columnAnnotation.name());
            } else {
                columnName = field.getName();
            }
            columnList.add(StrUtil.format("{}", columnName));
        }
        return columnList;
    }


    /***
     *
     * @return 返回表的名称
     */
    private String getTableName() {
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (ObjectUtil.isNotNull(tableAnnotation)) {
            return StrUtil.format("{}", tableAnnotation.name());
        } else {
            return StrUtil.format("{}", clazz.getName().toLowerCase());
        }
    }

    /***
     *
     * @param t 表的对象
     * @return 表的名称
     */
    private String getTableName(T t) {
        Table tableAnnotation = t.getClass().getAnnotation(Table.class);
        if (ObjectUtil.isNotNull(tableAnnotation)) {
            return StrUtil.format("{}", tableAnnotation.name());
        } else {
            return StrUtil.format("{}", t.getClass().getName().toLowerCase());
        }
    }


    /***
     *
     * @param t
     * @return 对象列表
     */
    public List<T> findByExample(T t) {
        String tableName = getTableName(t);
        System.out.println(tableName);
//        得到t中的字段信息
        List<Field> filterList = getField(t, true);
        System.out.println(filterList);
//        列信息列表
        List<String> columnList = getColumns(filterList);
//        列信息
        List<String> columns = columnList.stream().map(s -> " and " + s + " = ? ").collect(Collectors.toList());
        String where = StrUtil.join(" ", columns);

//        参数
        Object[] values = filterList.stream().map(field -> ReflectUtil.getFieldValue(t, field)).toArray();

        // SQl语句
        String sql = StrUtil.format("SELECT * FROM {table} where 1 = 1 {where}", Dict.create().set("table", tableName).set("where", StrUtil.isBlank(where) ? "" : where));
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, values);

        List<T> ret = CollUtil.newArrayList();

        maps.forEach(map -> ret.add(BeanUtil.fillBeanWithMap(map, ReflectUtil.newInstance(clazz), true, false)));
        return  ret;
    }


    /***
     *  主键查询某个字段
     * @param pk
     * @return 单个字段
     */
    public T  findById(P pk){
        String tableName = getTableName();
        String sql = StrUtil.format("select * from {table} where id = ?", Dict.create().set("table",tableName));
        RowMapper<T> rowMapper =new BeanPropertyRowMapper<>(clazz);

        return jdbcTemplate.queryForObject(sql,new Object[]{pk},rowMapper);
    }

    /***
     * 根据主键更新
     * @param t
     * @param pk
     * @param ignoreNull
     * @return操作的行数
     */
    protected Integer updateById(T t,P pk,Boolean ignoreNull){
        String tableName = getTableName(t);
        List<Field> fieldList = getField(t,true);
        List<String> columnList = getColumns(fieldList);

        List<String> columns = columnList.stream().map(s -> StrUtil.appendIfMissing(s,"= ? ")).collect(Collectors.toList());
        String params = StrUtil.join(Const.SEPARATOR_COMMA,columns);

        List<Object> valueList =fieldList.stream().map(field -> ReflectUtil
                .getFieldValue(t,field)).collect(Collectors.toList());
        valueList.add(pk);

        Object[] values = ArrayUtil.toArray(valueList,Object.class);

        String sql = StrUtil.format("update {table} set {params} where id = ?",
                Dict.create().set("table",tableName).set("params",params));

        return jdbcTemplate.update(sql,values);
    }


    /***
     *  根据主键删除
     * @param pk
     * @return 行数
     */
    protected Integer deleteById(P pk){
        String tableName = getTableName();
        String sql = StrUtil.format("delete from {table} where id = ?",Dict.create().set("table",tableName));

        return jdbcTemplate.update(sql,pk);
    }


    /***
     * 插入操作
     * @param t
     * @param ignoreNull
     * @return 插入的行数
     */
    protected Integer insert(T t,Boolean ignoreNull){
        String tableName = getTableName(t);
        List<Field> filterField = getField(t,true);
        List<String> columnList = getColumns(filterField);

        String columns = StrUtil.join(Const.SEPARATOR_COMMA,columnList);

//        params
        String params = StrUtil.repeatAndJoin("?",columnList.size(),",");

        Object[] values =filterField.stream().map(field -> ReflectUtil.getFieldValue(t,field)).toArray();

        String sql = StrUtil.format("insert into {table} ({columns}) values ({params})",
                Dict.create().set("table",tableName)
                        .set("columns",columns)
                        .set("params",params));

        return jdbcTemplate.update(sql,values);


    }
}

