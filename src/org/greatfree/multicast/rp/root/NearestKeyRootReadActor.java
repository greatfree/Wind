package org.greatfree.multicast.rp.root;

import java.io.IOException;

import org.greatfree.concurrency.Notifier;
import org.greatfree.exceptions.DistributedNodeFailedException;

// Created: 10/20/2018, Bing Li
class NearestKeyRootReadActor implements Notifier<NearestKeyMulticastRequest>
{
	private RootSyncMulticastor multicastor;
	
	public NearestKeyRootReadActor(RootSyncMulticastor multicastor)
	{
		this.multicastor = multicastor;
	}

	@Override
	public void notify(NearestKeyMulticastRequest message)
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

	/*
	@Override
	public void perform(NearestKeyMulticastRequest message, int cryptoOption)
	{
		// TODO Auto-generated method stub
		
	}
	*/

}
