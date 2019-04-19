package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Eventhandle;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface EventhandleMapper extends BaseMapper<Eventhandle> {
    /*
    * 查找所有历史任务
    * */
    List<Eventhandle> queryAll(Long id);
    /*
    * 查询完成的审核通过的历史任务
    * */
    List<Eventhandle> queryHistory(Long id);

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
    * 查询需要完成的任务
    * */
    List<Eventhandle> queryNow(Long id);

    /*
    * 任务提交,根据当前任务的id修改状态
    * */
    void submit(Long id);

    /*
    * 正在执行的任务，按照时间最新的来选
    * */
    Eventhandle eventNow(Long id);

    /*
    * 任务提交，可以上传信息的接口
    * */
    void newsubmit(@Param("id") Long id, @Param("subdescrib")String subdescrib,@Param("subphotopath")String subphotopath);

    /*
    * 审核通过的接口
    * */
    void pass(Long id);

    /*
    * 审核驳回的接口
    * */
    void reject(@Param("id")Long id,@Param("reject") String reject);

    /*
    * 查询我的所有待审的任务
    * */
    List<Eventhandle> findWait(Long id);

    /*
     * 查询我的所有审核通过的任务
     * */
    List<Eventhandle> findPass(Long id);
    /*
     * 查询我的所有审和不通过的任务
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
     * @param id         任务id
     * @param fileName   上传的文件名，多个文件名中间用逗号隔开
     * @param subdescrib 上传的提交内容
     * @return  操作成功返回1否则返回0
     */
    @Update("UPDATE `eventhandle` SET `Status`='1', subdescribe=#{subdescrib}, `subphotopath`=#{fileName} WHERE id=#{id}")
    int submitEventhandle(@Param("id")Long id,@Param("fileName")String fileName,@Param("subdescrib")String subdescrib);


    /**
     * 更改绑定手机
     * @param phone    老手机号码
     * @param newPhone 新手机号码
     * @return 操作成功返回1否则返回0
     */
    @Update("UPDATE `employee` SET `headimage`=#{newPhone} WHERE (`#{phone}=#{phone})")
    int updatePhone(@Param("phone") String phone,@Param("newPhone")String newPhone);

    /**
     * 自定义发布任务
     * @param eventhandle 封装的任务信息
     * @return 成功返回1否则返回0
     */
    @Insert("INSERT INTO `eventhandle` (`eventnoticeID`, `describe`, `employeeID`, `Status`, `CreateDate`, `Address`, `subphotopath`)" +
            " VALUES (#{eventnoticeID},#{describe},#{employeeID},#{Status},#{CreateDate},#{Address},#{subphotopath})")
    int postEventhandleDIY(Eventhandle eventhandle);

    /*
    * 管理层发布任务的接口
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