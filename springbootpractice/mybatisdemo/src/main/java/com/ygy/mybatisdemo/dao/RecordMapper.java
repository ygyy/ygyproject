package com.ygy.mybatisdemo.dao;

import com.ygy.mybatisdemo.domain.Book;
import com.ygy.mybatisdemo.domain.Record;

import java.util.Date;
import java.util.List;

public interface RecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer recordid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int insert(Record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int insertSelective(Record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    Record selectByPrimaryKey(Integer recordid);

    /**
     * 根据用户id和图书id查询记录
     * @param userid
     * @param bookid
     * @return
     */
    Record selectByuseridandbookid(Integer userid,Integer bookid);

    /**
     * 查询超出日期的书籍
     * @param userid
     * @return
     */

    List<Record> selectoverreport(Integer userid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Record record);
    /**
     * 获取所有记录
     * @return
     */
    List<Record> getAll();//获取所有

}