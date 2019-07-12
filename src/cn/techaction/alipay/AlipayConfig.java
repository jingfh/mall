package cn.techaction.alipay;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.jni.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



import java.net.InetAddress;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id ="2016101100661743";
	
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBsX6QRvHsD/snVmfnSI1GSo3j+RHmOZ1WRZDtmZIx4S5Uu/0n5JhaMqJDR4nre6cSM6M+ytGbqwWacfKtd7NCU+9OZ3e2+igW2BO0aMN75FlS73WX8PwNmZRMriMuO3UxC35y0hBKwJ6Vvu+hq192XABxrpH7Wf2+/OxVVChiu7cVLMk2Z9Vrfn70RFf2R8N4yJAfXjNogZKEQJu/IQHItY8tR9fS0v+MUhpeJZJb3De3q/Gj05284SN6hnMWYISC2GhrvWjBuhpdCHpR6oDl9X/DcY4BaCYUPKfWR63x9cdARuafj52t4itfaXPcKGC+AbknW7wXp2c4lK6QgPa1AgMBAAECggEASkBldpK3lPA9WXM1v4uhHno2rCuAHPAlbSwc4ntG4PnL/cU3FezhcNqGXcCKbOB5cYlQz/VsdhQ+/AJ67ueWIMZPPvMr7f1gkFubRHGqYbcFkN7500ir9dlUt2YHTEPMP40WEwTDQRjwcklDhqrH18qqkmtgtqf6B6tkXyH0kTmX9nNUH9R5rkmkIY2PRVOy6QOPasRj53hYgWdMcr7f1ifCuGN4laWzIAnC6NsC5oOtje3WFUo2XpkNf5cOL486sKppVN1c20DjRGbSH0sHAeQgi7H+GUjtjxxUwLbKat2gMNcsQcnYeDO9sexcybpwIMs2DJ2VWhQP6Vaziam8gQKBgQDIA2gY18H5u/bYIDp1rNUsagH7Ku2W7ZU0mI/UVO26sHK3T/ZjfRALf0KaiHY04NZkztxYkW/M0IDO+bYueR9pkVGjNGSLxRguQWiC82dfTukW6oWE/OGAtYXThgJ1ZuUES9Mn2GPP5B8j5Wh4+9wKuu7ArqGleQmhB0hWuwfmoQKBgQCl/xXbPk9xfVv7i9Wkgb49e4dWhEmJ/38O6DEXJXU67e9W2a4oGIEL0teLqUvOz675szHAViHF9dfw1KYeNgrT6xlABMq7SpsRkDhKISkBlama3emkTcMf73DuI0Ghk5LBwDpDF/5sYMb69zydFrV2n6Y3KvurdlhU0kkxGRzblQKBgA6Y6Qz/GiIOzMojOAEElx5BsllRXbupJT2McNrPQ/ZfEFA+nr3OD9C2/fZnRTFAw4Th8ATqwF0TToNMiDZGErvSpkGh/kCRJt2hWm5MM1wqVyqb/RiimBabqOOyIRXOZ6JzojqDOW2fuYIWKRTDVODXmbsoQ5XfCovdCzOBPOdhAoGBAIODlPpt5J0i18R05l/+lw25F+G4z1axASZ7WaFgAQAeUNh7gFquB1epxTIWgEQrERMuIMWkt/9SJrLcl3wqDuwVQJ0S7tfyZOdGXwKEQSvLOmPDW3/cSDK+DrftYOs1szkkJkTP9rhA+IUPaot+2WhRVmww9tHC4nrZXDm1DsL9AoGAAtXdPM55gZBstryB3tghngUN4K5x3gIleA1HOUYfU/0w8rey4MFjvrwXqgV6bZ5m849Gj1EnC1AP7Lgr0TzUpTITyITVmmNC02gaOEFYl23S8QKKKxaGsBXXsYohZfcbjCJmm44jabgQAgdQtAbrBePeyd19tjupbvry9aGvw3g=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtuz3pcElKhke1uq6+DhsMJFMYo1poR/0Bd6Scl3K8t0WwXSUEoJMzkIWkQJMcYC4RPUpD6J/qGcP9o3CgNXXTuQY/bFOOMgImaEDMnqzvKKii/hG/qq/4GmJArW1ZHdtl5RLXztvfr5LnH9PZhF9kNVBE2jUWbiLFvzN75ZuO6GgjY+/9kkB3VCE4rl8ExfU36b83P/tiKlBTxi+i6hTv8NudQTZAZpA2+yBFq5epZ0HJwqStYEgg8k3Otlq30kPsDC99GPuyEkQNwU7tkQf4OZXSQJtCu8gaP3BSZhgrl7wtJbEV3ypSGEabxheEKboFGQENgwTvWAs8EJq5nVEWQIDAQAB";

////////////////////////////////////////////////////////////////////////////////    
    
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String notify_url = "http://localhost:8080/mall/notify_url.jsp";
    public static String notify_url = "";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/mall/return_url.jsp";
	//public static String return_url = "http://localhost:8080/mall/order_list.html";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:/学习资料/日志/";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /*
    @Autowired
    private static HttpServletRequest request;
    
    @RequestMapping("/test")
    public void test() throws InterruptedException{
    	String path = request.getServletContext().getRealPath("/");//获取项目路径
    	System.out.println(path);
    }
    */
   
    
    
    //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    //HttpSession session = ServletActionContext.getRequest();
    //System.out.println(  System.getProperty("rootPath")   );
    
}

