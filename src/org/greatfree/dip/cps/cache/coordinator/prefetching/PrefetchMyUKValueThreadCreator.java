package org.greatfree.dip.cps.cache.coordinator.prefetching;

import org.greatfree.concurrency.reactive.NotificationObjectThreadCreatable;
import org.greatfree.dip.cps.cache.message.postfetch.FetchMyUKValueNotification;

// Created: 02/25/2019, Bing Li
public class PrefetchMyUKValueThreadCreator implements NotificationObjectThreadCreatable<FetchMyUKValueNotification, PrefetchMyUKValueThread>
{

	@Override
	public PrefetchMyUKValueThread createNotificationThreadInstance(int taskSize)
	{
		return new PrefetchMyUKValueThread(taskSize);
	}

}
