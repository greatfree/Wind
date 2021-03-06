package org.greatfree.framework.cps.cache.coordinator.front;

import org.greatfree.concurrency.reactive.RequestQueueCreator;
import org.greatfree.framework.cps.cache.message.front.LoadMyDataRequest;
import org.greatfree.framework.cps.cache.message.front.LoadMyDataResponse;
import org.greatfree.framework.cps.cache.message.front.LoadMyDataStream;

// Created: 07/09/2018, Bing Li
public class LoadMyDataThreadCreator implements RequestQueueCreator<LoadMyDataRequest, LoadMyDataStream, LoadMyDataResponse, LoadMyDataThread>
{

	@Override
	public LoadMyDataThread createInstance(int taskSize)
	{
		return new LoadMyDataThread(taskSize);
	}

}
