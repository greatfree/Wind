package org.greatfree.dip.cps.cache.coordinator.prefetching;

import org.greatfree.concurrency.reactive.NotificationObjectThreadCreatable;
import org.greatfree.dip.cps.cache.message.PrefetchMyCachePointingListStoreNotification;

// Created: 08/03/2018, Bing Li
public class PrefetchMyPointingListStoreThreadCreator implements NotificationObjectThreadCreatable<PrefetchMyCachePointingListStoreNotification, PrefetchMyPointingListStoreThread>
{

	@Override
	public PrefetchMyPointingListStoreThread createNotificationThreadInstance(int taskSize)
	{
		return new PrefetchMyPointingListStoreThread(taskSize);
	}

}
