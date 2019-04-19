package com.demo.service.impl;


import com.demo.base.BaseServiceImpl;
import com.demo.domain.Eventhandle;
import com.demo.mapper.EventhandleMapper;
import com.demo.mapper.UploadMapper;
import com.demo.service.IEventhandleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class EventhandleServiceImpl extends BaseServiceImpl<Eventhandle> implements IEventhandleService {

    @Resource
    private EventhandleMapper eventhandleMapper;

    @Resource
    private UploadMapper uploadMapper;
    /*
    * 查找所有历史任务根据用户id*/
    @Override
    public List<Eventhandle> queryAll(Long id) {
        return eventhandleMapper.queryAll(id);
    }

    @Override
    public List<Eventhandle> queryHistory(Long id) {
        return eventhandleMapper.queryHistory(id);
    }

    @Override
    public List<Eventhandle> queryNow(Long id) {
        return eventhandleMapper.queryNow(id);
    }

    @Override
    public Eventhandle eventNow(Long id) {
        Eventhandle eventhandle = eventhandleMapper.eventNow(id);
        return eventhandle;
    }

    @Override
    public List<Eventhandle> queryToday(Long id) {
        return eventhandleMapper.queryToday(id);
    }

    @Override
    public List<Eventhandle> queryPastDay(Long id, Integer day) {
        return eventhandleMapper.queryPastDay(id, day);
    }

    @Override
    public List<Eventhandle> queryPastMonth(Long id, Integer month) {
        return eventhandleMapper.queryPastMonth(id, month);
    }

    @Override
    @Transactional
    public void submit(Long id) {
        eventhandleMapper.submit(id);
    }


    /*
    * 任务提交
    * */
    @Transactional
    @Override
    public void newsubmit(Long id, String subdescrib,String subphotopath) {
        //保存信息
        eventhandleMapper.newsubmit(id, subdescrib,subphotopath);
    }
    /*
     * 审核通过
     * */
    @Transactional
    @Override
    public void pass(Long id) {
        eventhandleMapper.pass(id);
    }

    /*
    * 审核不通过
    * */
    @Override
    public void reject(Long id, String reject) {
        eventhandleMapper.reject(id, reject);
    }

    //查询待审的任务
    @Override
    public List<Eventhandle> findWait(Long id) {
        return eventhandleMapper.findWait(id);
    }

    @Override
    public List<Eventhandle> findPass(Long id) {
        return eventhandleMapper.findPass(id);
    }

    @Override
    public List<Eventhandle> findReject(Long id) {
        return eventhandleMapper.findReject(id);
    }

    @Override
    public List<Eventhandle> listAll() {
        return eventhandleMapper.listAll();
    }

    @Override
    public List<Eventhandle> listNow() {
        return eventhandleMapper.listNow();
    }

    @Override
    public List<Eventhandle> listWait() {
        return eventhandleMapper.listWait();
    }

    @Override
    public Map<String, BigDecimal> queryType() {
        return eventhandleMapper.queryType();
    }

    @Override
    public Map<String, BigDecimal> typeNew(Long id) {
        return eventhandleMapper.typeNew(id);
    }

    @Override
    public Map<String, BigDecimal> typeNow(Long id) {
        return eventhandleMapper.typeNow(id);
    }

    @Override
    public Map<String, BigDecimal> typeWait(Long id) {
        return eventhandleMapper.typeWait(id);
    }
	/*@Override
    public boolean submitEventhandle(Long id, MultipartFile[] files,String subdescrib) {
        StringBuffer fileName = new StringBuffer();
        try {
            for(int index = 0 ;files != null && index < files.length ;index++){
                if(index == 0){
                    fileName.append(FileUtil.saveFile(files[index]));
                }else{
                    fileName.append("," + FileUtil.saveFile(files[index]));
                }
            }
            return eventhandleMapper.submitEventhandle(id,fileName.toString(),subdescrib) != 0;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }*/

    @Override
    public boolean concernEventhandle(Long depId, Long eveId) {
        return true;
    }

    @Override
    public boolean deleteMyConcern(int depId, int eveId) {
        return true;
    }



    @Override
    public boolean updatePhone(String phone, String newPhone) {
        return eventhandleMapper.updatePhone(phone,newPhone) !=0;
    }

   /* @Override
    public boolean postEventhandleDIY(Eventhandle eventhandle, MultipartFile[] files) {
        StringBuffer fileName = new StringBuffer();
        try {
            for(int index = 0 ;files != null && index < files.length ;index++){
                if(index == 0){
                    fileName.append(FileUtil.saveFile(files[index]));
                }else{
                    fileName.append("," + FileUtil.saveFile(files[index]));
                }
            }
            eventhandle.setSubphotopath(fileName.toString());
            return eventhandleMapper.postEventhandleDIY(eventhandle) != 0;
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }*/

    @Override
    public void issue(Eventhandle eventhandle) {
        eventhandleMapper.issue(eventhandle);
    }

    @Override
    public List<Eventhandle> eventAllIssue() {
        return eventhandleMapper.eventAllIssue();
    }

    @Override
    public List<Eventhandle> eventAllDonne() {
        return eventhandleMapper.eventAllDonne();
    }
}
