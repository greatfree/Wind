package org.greatfree.cluster.child;

import org.greatfree.concurrency.reactive.NotificationQueueCreator;
import org.greatfree.framework.multicast.message.RootIPAddressBroadcastNotification;

// Created: 09/23/2018, Bing Li
class RootIPAddressBroadcastNotificationThreadCreator implements NotificationQueueCreator<RootIPAddressBroadcastNotification, RootIPAddressBroadcastNotificationThread>
{

	@Override
	public RootIPAddressBroadcastNotificationThread createInstance(int taskSize)
	{
		return new RootIPAddressBroadcastNotificationThread(taskSize);
	}

}
