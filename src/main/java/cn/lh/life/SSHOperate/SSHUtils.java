package cn.lh.life.SSHOperate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SSHUtils {

	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	public static final String ORACLEUSER = "system";
	public static final String ORACLEPASSWORD = "welcome1";
	public static final String ORACLEIP = "10.126.9.65";
	public static final String DBINSTANCE = "cldb";
	public static final String DUMPDIR = "DUMPDIR";
	public static final String DBUSERS = "CMS,EBANK,BEDC,RDP,V7CW,OPS,WFLTEST,SMARTBI,CQI";


	private static String DEFAULTCHARTSET = "UTF-8";
	private static Connection conn;
	private static StringBuffer outBuffer = new StringBuffer();
	private static StringBuffer errBuffer = new StringBuffer();

	/**
	 *
	 * @Title: login
	 * @Description: 用户名密码方式  远程登录linux服务器
	 * @return: Boolean
	 * @throws
	 */
	public static Boolean login(RemoteConnect remoteConnect) {
		boolean flag = false;
		try {
			conn = new Connection(remoteConnect.getIp());
			conn.connect();// 连接
			flag = conn.authenticateWithPassword(remoteConnect.getUserName(), remoteConnect.getPassword());// 认证
			if (flag) {
				System.out.println("认证成功！");
			} else {
				System.out.println("认证失败！");
				conn.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 *
	 * @Title: execute
	 * @Description: 远程执行shll脚本或者命令
	 * @param cmd 脚本命令
	 * @return: result 命令执行完毕返回结果
	 * @throws
	 */
	public static void execute(String cmd){

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Session session = conn.openSession();// 打开一个会话
					session.execCommand(cmd);// 执行命令
					processStdout(session.getStdout(), DEFAULTCHARTSET);

					conn.close();
					conn = null;
					session.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}


	/**
	 *
	 * @Title: processStdout
	 * @Description: 解析脚本执行的返回结果
	 * @param in 输入流对象
	 * @param charset 编码
	 * @return String 以纯文本的格式返回
	 * @throws
	 */
	public static void processStdout(InputStream in, String charset){
		InputStream stdout = new StreamGobbler(in);
		try {
			BufferedReader outBr = new BufferedReader(new InputStreamReader(stdout, charset));
			String outLine = null;

			BufferedReader errBr = new BufferedReader(new InputStreamReader(stdout, charset));
			String errLine = null;

			while ((outLine = outBr.readLine()) != null) {
				outBuffer.append(outLine + "\n");
			}

			while ((errLine = errBr.readLine()) != null) {
				errBuffer.append(errLine + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Map<String, Object> getBuffer(){
		//命令执行完成标志
		boolean endflag = false;
		if (conn == null) {//如果连接已关闭，则表示命令执行完成，否则不完成
			endflag = true;
		}
		String outInfo = "";
		String errInfo = "";
		synchronized (outBuffer) {
			outInfo = outBuffer.toString();
			outBuffer.delete(0, outBuffer.length());
		}
		synchronized (errBuffer) {
			errInfo = errBuffer.toString();
			errBuffer.delete(0, errBuffer.length());
		}

		Map<String, Object> map = new HashMap<>();
		map.put("outInfo", outInfo);
		map.put("errInfo", errInfo);
		map.put("endflag", endflag);

		return map;
	}
}
