package com.wang.blog.repository;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import com.wang.blog.pojo.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @version 1.0
 * @Auther wangchengyang
 * @Date 2021/4/3 15:35
 */
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

    void deleteNoticeByIdIn(List<Integer> ids);

}
