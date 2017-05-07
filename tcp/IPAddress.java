package tcp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import jdk.internal.dynalink.beans.StaticClass;

public class IPAddress {
	public static InetAddress getIPloopback(){
		InetAddress loopback=null;
		try {
			loopback = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loopback;
	}
	
	public static InetAddress getConnectedIP(){
		Enumeration e=null;
		InetAddress ip= null;
		try {
			e = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while(e.hasMoreElements())
		{
			NetworkInterface n = (NetworkInterface) e.nextElement();
			boolean connected =false;
			try {
				connected = n.isUp() & !n.isLoopback();
			} catch (SocketException e1) {
				e1.printStackTrace();
			}
			if (connected){
				Enumeration ee = n.getInetAddresses();
				while (ee.hasMoreElements())
				{
					ip = (InetAddress) ee.nextElement();
				}
			}
		}
		return ip;
	}
	
	public static void main(String[] args) {
		System.out.println(IPAddress.getIPloopback());
		System.out.println(IPAddress.getConnectedIP());
	}
}
