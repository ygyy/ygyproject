package com.ygy.mybatisdemo.controller;

import com.ygy.mybatisdemo.domain.Book;
import com.ygy.mybatisdemo.domain.Record;
import com.ygy.mybatisdemo.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class RecordController {
    @Autowired
    private RecordService recordService;

    /**
     * 查询所有图书
     * @return
     */
    @ResponseBody
    @GetMapping("/book/books")
    public List<Book> getBookList(){
        return recordService.getBookList();
    }

    //前端测试时日期参数格式Sat, 26 May 2020 10:00:00
    /**
     *预约
     * @param userid
     * @param bookid
     * @param date
     * @return
     */
    @ResponseBody
    @PostMapping("/book/books/{userid}/{bookid}/{date}")
    public boolean orderBook(Integer userid, Integer bookid, Date date){
        return recordService.orderBook(userid,bookid,date);
    }

    /**
     * 借书
     * @param userid
     * @param bookid
     * @param lenddate
     * @param lenddeadline
     * @return
     */
    @ResponseBody
    @PutMapping("/book/books/{userid}/{bookid}/{lenddate}/{lenddeadline}")
    public boolean borrowBook(Integer userid,Integer bookid,Date lenddate,Date lenddeadline){
        return recordService.borrowBook(userid,bookid,lenddate,lenddeadline);
    }

    /**
     * 还书
     * @param userid
     * @param bookid
     * @param returndate
     * @return
     */
    @ResponseBody
    @PutMapping("/book/books/{userid}/{bookid}/{returndate}")
    public boolean returnBook(Integer userid,Integer bookid,Date returndate){
        return recordService.returnBook(userid,bookid,returndate);
    }

    /**
     * 逾期图书报告
     * @param userid
     * @return
     */
    @ResponseBody
    @GetMapping("/book/books/{userid}")
    public List<Record> overdueReport(Integer userid){
        return recordService.overdueReport(userid);
    }

}
