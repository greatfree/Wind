package org.greatfree.message.multicast.container;

import org.greatfree.message.multicast.MulticastMessageType;
import org.greatfree.message.multicast.MulticastRequest;

// Created: 09/23/2018, Bing Li
public abstract class Request extends MulticastRequest
{
	private static final long serialVersionUID = 8212022646043424214L;
	
	private String clientKey;
	private int requestType;
	private int applicationID;

	/*
	 * The constructor is usually used for the nearest unicasting. So the client key is required for nearest measure. 10/28/2018, Bing Li 
	 */
//	public Request(String clientKey, int requestType, int applicationID)
	public Request(String clientKey, int applicationID)
	{
		super(MulticastMessageType.REQUEST);
		this.clientKey = clientKey;
//		this.requestType = requestType;
		this.requestType = MulticastMessageType.UNICAST_REQUEST;
		this.applicationID = applicationID;
	}

	/*
	 * The below two constructors are used together for the root and the children, respectively. 10/28/2018, Bing Li
	 */

	/*
	 * This constructor is usually used for normal broadcasting. 10/28/2018, Bing Li
	 */
	public Request(int requestType, int applicationID)
	{
		super(MulticastMessageType.REQUEST);
		this.requestType = requestType;
		this.applicationID = applicationID;
	}

	/*
	 * This constructor is usually used for normal broadcasting. 10/28/2018, Bing Li
	 */
	/*
	public Request(int requestType, int applicationID, Map<String, IPAddress> childrenIPs)
	{
		super(ClusterMessageType.REQUEST, childrenIPs);
		this.requestType = requestType;
		this.applicationID = applicationID;
	}
	*/
	
	/*
	 * The below two constructors are used together for the root and the children, respectively. 10/28/2018, Bing Li
	 */

	/*
	 * The below design results in a cumbersome class. So such system level requests are available until now. So the below design should be removed. 10/28/2018, Bing Li
	 * 
	 * For some system messages, it is unnecessary to assign the application ID. 10/28/2018, Bing Li
	 */
	/*
	public Request(int requestType)
	{
		super(ClusterMessageType.REQUEST);
		this.requestType = requestType;
	}
	*/

	/*
	 * For some system messages, it is unnecessary to assign the application ID. 10/28/2018, Bing Li
	 */
	/*
	public Request(int requestType, Map<String, IPAddress> childrenIPs)
	{
		super(ClusterMessageType.REQUEST, childrenIPs);
		this.requestType = requestType;
	}
	*/
	
	public String getClientKey()
	{
		return this.clientKey;
	}

	public int getRequestType()
	{
		return this.requestType;
	}
	
	public int getApplicationID()
	{
		return this.applicationID;
	}
}
