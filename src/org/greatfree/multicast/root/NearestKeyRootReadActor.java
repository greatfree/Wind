package org.greatfree.multicast.root;

import java.io.IOException;

import org.greatfree.concurrency.Async;
import org.greatfree.exceptions.DistributedNodeFailedException;

// Created: 09/16/2018, Bing Li
class NearestKeyRootReadActor extends Async<NearestKeyMulticastRequest>
{
	private RootSyncMulticastor multicastor;
	
	public NearestKeyRootReadActor(RootSyncMulticastor multicastor)
	{
		this.multicastor = multicastor;
	}

	@Override
	public void perform(NearestKeyMulticastRequest message)
	{
		try
		{
			this.multicastor.nearestRead(message.getDataKey(), message.getRequest());
		}
		catch (IOException | DistributedNodeFailedException e)
		{
			e.printStackTrace();
		}
	}

}
