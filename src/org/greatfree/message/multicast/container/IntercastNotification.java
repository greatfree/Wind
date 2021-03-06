package org.greatfree.message.multicast.container;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.greatfree.message.ServerMessage;
import org.greatfree.message.multicast.MulticastMessageType;
import org.greatfree.util.IPAddress;
import org.greatfree.util.UtilConfig;

// Created: 01/26/2019, Bing Li
// public abstract class IntercastNotification extends Notification
public abstract class IntercastNotification extends ServerMessage
{
	private static final long serialVersionUID = 1070974284416017887L;

	private int applicationID;
	private int intercastType;
	private String sourceKey;
	private String destinationKey;
	private Set<String> destinationKeys;
	
	private IPAddress destinationIP;
	private Set<IPAddress> destinationIPs;
	
	// Different from destination keys, which are generated by the application-level ID, the children keys are generated by the IP addresses of children in the clusters. 02/28/2019, Bing Li
	private Map<String, Set<String>> childDestionations;
	
	// Now I need to implement the root based intercasting. So the IP address is not necessary to send to the child. I will implement the children-based intercasing later. 02/15/2019, Bing Li 
//	private IPAddress destinationIP;
	
	// According to the value, a child is able to identify whether it needs to forward the notification to the destination child or it itself is the destination child and it needs to process the message. 02/09/2019, Bing Li
//	private boolean isForwarded;

	public IntercastNotification(String srcKey, String dstKey, int applicationID)
	{
//		super(srcKey, MulticastMessageType.INTERCAST_NOTIFICATION, applicationID);
		super(MulticastMessageType.INTERCAST_NOTIFICATION);
		this.applicationID = applicationID;
//		super(dstKey, MulticastMessageType.INTERCAST_NOTIFICATION, applicationID);
		this.intercastType = MulticastMessageType.INTER_UNICAST_NOTIFICATION;
		this.sourceKey = srcKey;
		this.destinationKey = dstKey;
		this.destinationKeys = null;
//		this.isForwarded = false;
		this.childDestionations = new HashMap<String, Set<String>>();
	}
	
	public IntercastNotification(int intercastType, String srcKey, Set<String> dstKeys, int applicationID)
	{
//		super(srcKey, MulticastMessageType.INTERCAST_NOTIFICATION, applicationID);
		super(MulticastMessageType.INTERCAST_NOTIFICATION);
		this.applicationID = applicationID;
		this.intercastType = intercastType;
		this.sourceKey = srcKey;
		this.destinationKey = UtilConfig.EMPTY_STRING;
		this.destinationKeys = dstKeys;
		this.childDestionations = new HashMap<String, Set<String>>();
	}
	
	public int getApplicationID()
	{
		return this.applicationID;
	}
	
	public int getIntercastType()
	{
		return this.intercastType;
	}

	public String getSourceKey()
	{
		return this.sourceKey;
	}
	
	public String getDestinationKey()
	{
		return this.destinationKey;
	}
	
	public Set<String> getDestinationKeys()
	{
		return this.destinationKeys;
	}

	public void setDestinationIP(IPAddress ip)
	{
		this.destinationIP = ip;
	}
	
	public IPAddress getDestinationIP()
	{
		return this.destinationIP;
	}
	
	public void setChildDestination(Map<String, Set<String>> cds)
	{
		this.childDestionations = cds;
	}
	
	public Map<String, Set<String>> getChildDestinations()
	{
		return this.childDestionations;
	}
	
	public void setDestinationIPs(Set<IPAddress> ips)
	{
		this.destinationIPs = ips;
	}
	
	public Set<IPAddress> getDestinationIPs()
	{
		return this.destinationIPs;
	}

	/*
	public void setForwarded()
	{
		this.isForwarded = true;
	}
	
	public boolean isForwarded()
	{
		return this.isForwarded;
	}
	*/
}
