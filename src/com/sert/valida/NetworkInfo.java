package com.sert.valida;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class NetworkInfo {
	public static String getMacAddress() throws SocketException, UnknownHostException {

		String ip = InetAddress.getLocalHost().getHostAddress();
		String loopBack = ip.substring(0, 3);
		if (loopBack.equals("127")) {
			ip = getFirstIP();
		}
		InetAddress localHost = InetAddress.getByName(ip);
		NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
		byte[] macAddressBytes = netInter.getHardwareAddress();

		String macAddress = String.format("%1$02x%2$02x%3$02x%4$02x%5$02x%6$02x", macAddressBytes[0],
				macAddressBytes[1], macAddressBytes[2], macAddressBytes[3], macAddressBytes[4], macAddressBytes[5])
				.toUpperCase();

		return macAddress;

	}

	static private String getFirstIP() {
		java.util.Enumeration<java.net.NetworkInterface> ifaces = null;

		try {
			ifaces = java.net.NetworkInterface.getNetworkInterfaces();
		} catch (java.net.SocketException e) {
		}

		for (; ifaces.hasMoreElements();) {
			java.util.Enumeration<java.net.InetAddress> addrs = ifaces.nextElement().getInetAddresses();

			for (; addrs.hasMoreElements();) {
				java.net.InetAddress addr = addrs.nextElement();
				if (!addr.isLoopbackAddress() && !(addr instanceof java.net.Inet6Address))
					return addr.getHostName();
			}

		}
		return "127.0.0.1";
	}
}