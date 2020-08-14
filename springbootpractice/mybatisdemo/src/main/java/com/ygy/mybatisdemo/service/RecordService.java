package com.ygy.mybatisdemo.service;

import com.ygy.mybatisdemo.domain.Book;
import com.ygy.mybatisdemo.domain.Record;

import java.util.Date;
import java.util.List;

public interface RecordService {

    /**
     * 获取所有图书信息
     * @return
     */
    public List<Book> getBookList();

    /**
     * 根据图书id查询图书状态，如果为0，则可预约，否则不可预约
     * 根据用户id预约
     * 修改图书状态为已预约
     * @param userid
     * @param bookid
     * @return
     */
    public boolean orderBook(int userid, int bookid, Date date);

    /**
     * 根据图书id查询图书状态，如果为0，则可借，否则不可借
     * 根据用户id借书
     * 修改图书状态为已借阅
     * @param userid
     * @param bookid
     * @param lenddate
     * @param lenddeadline
     * @return
     */
    public boolean borrowBook(int userid,int bookid,Date lenddate,Date lenddeadline);

    /**
     * 根据用户id和图书id还书，修改图书状态
     * @param userid
     * @param bookid
     * @return
     */
    public boolean returnBook(int userid,int bookid,Date returndate);

    /**
     * 根据用户id和还书日期，在record中查询所有超过还书截止日期的书籍
     * @param userid
     * @return
     */
    public List<Record> overdueReport(int userid);

}
