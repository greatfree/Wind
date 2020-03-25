package org.greatfree.testing.server;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.greatfree.client.ClientPoolSingleton;
import org.greatfree.client.IPResource;
import org.greatfree.testing.message.NodeKeyNotification;

/*
 * The class is derived from Thread. It is responsible for connecting the remote client such that it is feasible for the server to notify the client even without the client's request. 08/10/2014, Bing Li
 */

// Created: 08/10/2014, Bing Li
public class ConnectClientThread extends Thread
{
	// The queue keeps the clients' IPs which need to be connected to. 08/24/2014, Bing Li
	private Queue<IPResource> ipQueue;

	/*
	 * Initialize. 09/19/2014, Bing Li
	 */
	public ConnectClientThread()
	{
		this.ipQueue = new LinkedBlockingQueue<IPResource>();
	}

	/*
	 * Dispose the resource of the class. 09/19/2014, Bing Li
	 */
	public synchronized void dispose()
	{
		if (this.ipQueue != null)
		{
			this.ipQueue.clear();
			this.ipQueue = null;
		}
	}

	/*
	 * Input the IP address and the port number into the queue. They are connected in the order of first-in-first-out. The ClientPool is responsible for the connections. 09/19/2014, Bing Li
	 */
	public void enqueue(IPResource ipPort)
	{
		this.ipQueue.add(ipPort);
	}

	/*
	 * Connect the remote IP addresses and the associated port numbers concurrently. 09/19/2014, Bing Li
	 */
	public void run()
	{
		IPResource ipPort;
		// The thread keeps working until all of the IP addresses are connected. 09/19/2014, Bing Li
		while (this.ipQueue.size() > 0)
		{
			// Dequeue the IP addresses. 09/19/2014, Bing Li
			ipPort = this.ipQueue.poll();
			try
			{
				// Notify the remote node its unique ID. This is usually used to set up a multicasting cluster which contains a bunch of nodes. Each of them is identified by the ID. 09/20/2014, Bing Li
				ClientPoolSingleton.SERVER().getPool().send(ipPort, new NodeKeyNotification(ipPort.getObjectKey()));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
