package org.greatfree.dip.streaming.subscriber;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.dip.streaming.message.StreamNotification;

// Created: 03/20/2020, Bing Li
class StreamNotificationThreadCreator implements NotificationThreadCreatable<StreamNotification, StreamNotificationThread>
{

	@Override
	public StreamNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new StreamNotificationThread(taskSize);
	}

}
