package org.greatfree.testing.cluster.dn;

import java.io.IOException;
import java.util.Set;

import org.greatfree.client.ServerIORegistry;

/*
 * The class keeps all of DNIOs. This is a singleton wrapper of DNIORegistry. 11/11/2014, Bing Li
 */

// Created: 11/22/2016, Bing Li
public class DNIORegistry
{
	// Declare an instance of ServerIORegistry for DNIOs. 11/11/2014, Bing Li
	private ServerIORegistry<DNIO> registry;
	
	/*
	 * Initializing ... 11/11/2014, Bing Li
	 */
	private DNIORegistry()
	{
	}
	
	private static DNIORegistry instance = new DNIORegistry();
	
	public static DNIORegistry REGISTRY()
	{
		if (instance == null)
		{
			instance = new DNIORegistry();
			return instance;
		}
		else
		{
			return instance;
		}
	}

	/*
	 * Dispose the registry. 11/11/2014, Bing Li
	 */
	public void dispose() throws IOException, InterruptedException
	{
		this.registry.removeAllIOs();
	}

	/*
	 * Initialize the registry. 11/11/2014, Bing Li
	 */
	public void init()
	{
		this.registry = new ServerIORegistry<DNIO>();
	}

	/*
	 * Add a new instance of DNIO to the registry. 11/11/2014, Bing Li
	 */
	public void addIO(DNIO io)
	{
		this.registry.addIO(io);
	}

	/*
	 * Get all of the IPs of the connected clients from the corresponding DNIOs. 11/11/2014, Bing Li
	 */
	public Set<String> getIPs()
	{
		return this.registry.getIPs();
	}

	/*
	 * Get the count of the registered DNIOs. 11/11/2014, Bing Li
	 */
	public int getIOCount()
	{
		return this.registry.getIOCount();
	}

	/*
	 * Remove or unregister a DNIO. It is executed when a client is down or the connection gets something wrong. 11/11/2014, Bing Li
	 */
	public void removeIO(DNIO io) throws IOException, InterruptedException
	{
		this.registry.removeIO(io);
	}

	/*
	 * Remove or unregister all of the registered DNIOs. It is executed when the server process is shutdown. 11/11/2014, Bing Li
	 */
	public void removeAllIOs() throws IOException, InterruptedException
	{
		this.registry.removeAllIOs();
	}
}
