package org.greatfree.framework.streaming.unicast.child;

import org.greatfree.concurrency.reactive.NotificationQueueCreator;
import org.greatfree.framework.multicast.message.RootIPAddressBroadcastNotification;

// Created: 03/22/2020, Bing Li
class RootIPAddressBroadcastNotificationThreadCreator implements NotificationQueueCreator<RootIPAddressBroadcastNotification, RootIPAddressBroadcastNotificationThread>
{

	@Override
	public RootIPAddressBroadcastNotificationThread createInstance(int taskSize)
	{
		return new RootIPAddressBroadcastNotificationThread(taskSize);
	}

}
