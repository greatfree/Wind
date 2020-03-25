package org.greatfree.cluster.child.container;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.message.multicast.container.IntercastNotification;

// Created: 01/26/2019, Bing Li
class IntercastNotificationThreadCreator implements NotificationThreadCreatable<IntercastNotification, IntercastNotificationThread>
{

	@Override
	public IntercastNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new IntercastNotificationThread(taskSize);
	}

}


