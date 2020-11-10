package cn.lh.life.SSHOperate;

import java.util.HashMap;
import java.util.Map;

import cn.lh.life.framework.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常告警信息Controller
 * 
 * @author nts
 * @date 2020-10-16
 */
@Controller
@RequestMapping("/moudle/dbbackup")
public class DBBackUpController extends BaseController
{
    private String prefix = "moudle/dbbackup";
    
    @Autowired
    private IDBBackUpService dbBakUpService;
   
    /**
     * 执行数据库备份
     */
    @RequestMapping("/dbbackup")
    @ResponseBody
    public Map<String, Object> getAlarmFlag()
    {
    	Map<String, Object> executeDBBackUp = dbBakUpService.executeDBBackUp();
    	Map<String, Object> retMap = new HashMap<String, Object>();
    	retMap.put("success", executeDBBackUp.get("sucflag"));
    	retMap.put("result", executeDBBackUp.get("result"));
    	retMap.put("taketime", executeDBBackUp.get("taketime"));
    	
        return retMap;
    }
    




}
