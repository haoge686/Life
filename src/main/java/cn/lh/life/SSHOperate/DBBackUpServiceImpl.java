package cn.lh.life.SSHOperate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 异常告警信息Service业务层处理
 * 
 * @author nts
 * @date 2020-10-16
 */
@Service
public class DBBackUpServiceImpl implements IDBBackUpService 
{

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	public static final String ORACLEUSER = "system";
	public static final String ORACLEPASSWORD = "welcome1";
	public static final String ORACLEIP = "10.126.9.65";
	public static final String DBINSTANCE = "cldb";
	public static final String DUMPDIR = "DUMPDIR";
	public static final String DBUSERS = "CMS,EBANK,BEDC,RDP,V7CW,OPS,WFLTEST,SMARTBI,CQI";
	
	@Override
	public Map<String, Object> executeDBBackUp() {

		RemoteConnect remoteConnect = new RemoteConnect();
    	remoteConnect.setIp("192.168.0.5");
    	remoteConnect.setUserName("cms");
    	remoteConnect.setPassword("sunline2020");
    	Boolean login = SSHUtils.login(remoteConnect);
System.out.println(login);
    	String dateString = sdf.format(new Date());

    	String cmdString = "expdp " + ORACLEUSER + "/" + ORACLEPASSWORD + "@" + ORACLEIP + "/" + DBINSTANCE + " directory=" + DUMPDIR + " dumpfile=SCBF_" + dateString + ".dmp logfile=SCBF_" + dateString + ".log cluster=no schemas=" + DBUSERS;
System.out.println(cmdString);
System.out.println(new Date());
    	SSHUtils.execute(cmdString);
System.out.println(new Date());

    	Map<String, Object> retMap = new HashMap<String, Object>();
		return retMap;
	}
   
}
