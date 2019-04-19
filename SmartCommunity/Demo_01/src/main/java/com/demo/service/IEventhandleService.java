package com.demo.service;

import com.demo.base.IBaseService;
import com.demo.domain.Eventhandle;
import org.apache.ibatis.annotations.Param;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IEventhandleService extends IBaseService<Eventhandle> {

    /*
     * 查找所有历史任务
     * */
    List<Eventhandle> queryAll(Long id);
    /*
     * 查询完成的审核通过的历史任务
     * */
    List<Eventhandle> queryHistory(Long id);
    /*
     * 查询需要完成的任务
     * */
    List<Eventhandle> queryNow(Long id);
    /*
     * 正在执行的任务，按照时间最新的来选
     * */
    Eventhandle eventNow(Long id);

    /*
     * 查询当天完成的任务
     * */
    List<Eventhandle> queryToday(Long id);
    /*
     * 查询以前第n天完成的任务
     * */
    List<Eventhandle> queryPastDay(@Param("id") Long id, @Param("day") Integer day);
    /*
     * 查询以前第n月完成的任务
     * */
    List<Eventhandle> queryPastMonth(@Param("id") Long id, @Param("month") Integer month);

    /*
    *
    * 任务提交*/
    void submit(Long id);

    void newsubmit(@Param("id") Long id, @Param("subdescrib")String subdescrib,@Param("subphotopath")String subphotopath);
    //审核通过
    void pass(Long id);
    /*
    * 审核驳回
    * */
    void reject(@Param("id")Long id,@Param("reject") String reject);

    /*
     * 查询所有待审的任务
     * */
    List<Eventhandle> findWait(Long id);
    /*
     * 查询所有待审的任务
     * */
    List<Eventhandle> findPass(Long id);
    /*
     * 查询所有审和不通过的任务
     * */
    List<Eventhandle> findReject(Long id);

    //管理层，任务页面
    /*
     * 查询所有最新发布的任务
     * */
    List<Eventhandle> listAll();
    /*
     * 查询所有正在执行的任务
     * */
    List<Eventhandle> listNow();
    /*
     * 查询所有等待审核的任务
     * */
    List<Eventhandle> listWait();
    /*
     * 获取所有事件对应类型总数的接口
     * */
    Map<String, BigDecimal> queryType();
    /*
     * 获取所有事件对应类型总数的接口 (我的最新发布)
     * */
    Map<String, BigDecimal> typeNew(Long id);
    /*
     * 获取所有事件对应类型总数的接口  (我的正在处理)
     * */
    Map<String, BigDecimal> typeNow(Long id);
    /*
     * 获取所有事件对应类型总数的接口  (我的等待审核)
     * */
    Map<String, BigDecimal> typeWait(Long id);
	
	/**
     * 任务提交方法
     * @param id          任务id
     * @param files       任务相关图片集合
     * @param subdescrib  任务提交内容
     * @return  提交成功返回true否则返回fales
     */
    //boolean submitEventhandle(Long id,MultipartFile[] files,String subdescrib);

    /**
     * 关注任务方法
     * @param depId 用户id
     * @param eveId 任务id
     * @return 关注成功返回true否则返回fales
     */
    boolean concernEventhandle(Long depId,Long eveId);

    /**
     * 取消关注方法
     * @param depId 用户id
     * @param eveId 任务id
     * @return 取消成功返回true否则返回fales
     */
    boolean deleteMyConcern(int depId,int eveId);



    /**
     * 绑定新手机方法
     * @param phone    老手机号码
     * @param newPhone 新手机号
     * @return 绑定成功发怒会true否则返回fales
     */
    boolean updatePhone(String phone,String newPhone);

    /**
     * 自定义发布任务方法
     * @param eventhandle 任务对象
     * @param
     * @return 发布成功返回true否则返回fales
     */
    //boolean postEventhandleDIY(Eventhandle eventhandle,MultipartFile[] files);

    /*
    * 发布任务
    * */
    void issue(Eventhandle eventhandle);

    /*事件页面
     * 查找所有管理者发布的任务接口，正在执行
     * */
    List<Eventhandle> eventAllIssue();
    /*
     * 查找所有管理者发布的任务接口，已结完成
     * */
    List<Eventhandle> eventAllDonne();
}
