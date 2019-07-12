package cn.techaction.alipay;

import java.net.InetAddress;

public class testIP {

		public static void main(String[] args) 
				   throws Exception {
				      InetAddress addr = InetAddress.getLocalHost();
				      System.out.println("Local HostAddress: "+addr.getHostAddress());
				      //String hostname = addr.getHostName();
				      //System.out.println("Local host name: "+hostname);
				      InetAddress addr2 = InetAddress.getByName("");
		}

			
}
