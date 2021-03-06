package org.greatfree.multicast.root;

import java.io.IOException;

import org.greatfree.exceptions.DistributedNodeFailedException;

// Created: 09/10/2018, Bing Li
// class NearestRootEventActor extends Async<NearestMulticastMessage>
class NearestRootEventActor extends AsyncMulticastor<NearestMulticastNotification>
{
	public NearestRootEventActor(RootSyncMulticastor multicastor)
	{
		super(multicastor);
	}
	
	@Override
	public void perform(NearestMulticastNotification notification)
	{
		try
		{
			super.getMulticastor().nearestNotify(notification.getKey(), notification.getNotification());
		}
		catch (IOException | DistributedNodeFailedException e)
		{
			e.printStackTrace();
		}
	}

}
