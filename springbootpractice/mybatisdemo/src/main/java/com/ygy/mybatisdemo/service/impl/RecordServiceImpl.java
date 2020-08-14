package com.ygy.mybatisdemo.service.impl;

import com.ygy.mybatisdemo.dao.BookMapper;
import com.ygy.mybatisdemo.dao.RecordMapper;
import com.ygy.mybatisdemo.dao.UserMapper;
import com.ygy.mybatisdemo.domain.Book;
import com.ygy.mybatisdemo.domain.Record;
import com.ygy.mybatisdemo.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Resource
    private BookMapper bookMapper;
    @Resource
    private RecordMapper recordMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Book> getBookList() {
        return bookMapper.getAll();
    }

    @Override
    public boolean orderBook(int userid, int bookid, Date date) {
        Book book = bookMapper.selectByPrimaryKey(bookid);
        if(book.getBookstatus()==0){//可预约
            Record record = new Record();
            record.setBookid(bookid);
            record.setUserid(userid);
            record.setOrderdate(date);
            recordMapper.insert(record);//增加预约记录
            book.setBookstatus(2);//修改图书状态为已借阅
            bookMapper.updateByPrimaryKey(book);
            return true;
        }
        return false;
    }


    @Override
    public boolean borrowBook(int userid, int bookid, Date lenddate,Date lenddeadline) {
        Record rd = recordMapper.selectByuseridandbookid(userid, bookid);
        Book book = bookMapper.selectByPrimaryKey(bookid);
        if(book.getBookstatus()== 0){//可借
            Record record = new Record();
            record.setBookid(bookid);
            record.setUserid(userid);
            record.setLenddate(lenddate);
            record.setLenddeadline(lenddeadline);
            recordMapper.insert(record);//增加借书记录
            book.setBookstatus(1);//修改图书状态为已借阅
            bookMapper.updateByPrimaryKey(book);
            return true;
        }else if(book.getBookstatus() == 2 && rd.getOrderdate()!=null) {//已预约，可借
            rd.setLenddate(lenddate);
            rd.setLenddeadline(lenddeadline);
            recordMapper.updateByPrimaryKey(rd);
            book.setBookstatus(1);
            bookMapper.updateByPrimaryKey(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(int userid, int bookid,Date returndate) {
        Record record = recordMapper.selectByuseridandbookid(userid, bookid);
        if(record.getRecordid()!=null){
            record.setReturndate(returndate);
            System.out.println(record.getReturndate());
            recordMapper.updateByPrimaryKey(record);
            Book book = bookMapper.selectByPrimaryKey(bookid);
            book.setBookstatus(0);
            bookMapper.updateByPrimaryKey(book);
            return true;
        }
        return false;
    }

    @Override
    public List<Record> overdueReport(int userid) {

        return recordMapper.selectoverreport(userid);
    }
}
