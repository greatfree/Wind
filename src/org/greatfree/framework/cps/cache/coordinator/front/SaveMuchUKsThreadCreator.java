package org.greatfree.framework.cps.cache.coordinator.front;

import org.greatfree.concurrency.reactive.NotificationQueueCreator;
import org.greatfree.framework.cps.cache.message.front.SaveMuchUKsNotification;

// Created: 03/01/2019, Bing Li
public class SaveMuchUKsThreadCreator implements NotificationQueueCreator<SaveMuchUKsNotification, SaveMuchUKsThread>
{

	@Override
	public SaveMuchUKsThread createInstance(int taskSize)
	{
		return new SaveMuchUKsThread(taskSize);
	}

}
