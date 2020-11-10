package cn.lh.life.SSHOperate;

import java.util.Map;

/**
 * 异常告警信息Service接口
 * 
 * @author nts
 * @date 2020-10-16
 */
public interface IDBBackUpService 
{
    /**
     * 执行数据库备份脚本
     * 
     */
    public Map<String, Object> executeDBBackUp();

}
