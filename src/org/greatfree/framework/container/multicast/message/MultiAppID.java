package org.greatfree.framework.container.multicast.message;

// Created: 01/12/2019, Bing Li
public class MultiAppID
{
	public final static int CLIENT_HELLO_WORLD_ANYCAST_REQUEST = 90001;
	public final static int CLIENT_HELLO_WORLD_BROADCAST_REQUEST = 90002;
	public final static int CLIENT_HELLO_WORLD_UNICAST_REQUEST = 90003;
	
	public final static int HELLO_WORLD_ANYCAST_NOTIFICATION = 90004;

	public final static int HELLO_WORLD_ANYCAST_REQUEST = 90005;
	public final static int HELLO_WORLD_ANYCAST_RESPONSE = 90013;
	
	public final static int HELLO_WORLD_BROADCAST_NOTIFICATION = 90006;

	public final static int HELLO_WORLD_BROADCAST_REQUEST = 90007;
	public final static int HELLO_WORDL_BROADCAST_RESPONSE = 90012;
	
	public final static int HELLO_WORLD_UNICAST_NOTIFICATION = 90008;

	public final static int HELLO_WORLD_UNICAST_REQUEST = 90009;
	public final static int HELLO_WORLD_UNICAST_RESPONSE = 90013;
	
	public final static int STOP_CHILDREN_NOTIFICATION = 90010;
	public final static int STOP_ROOT_NOTIFICATION = 90011;
}