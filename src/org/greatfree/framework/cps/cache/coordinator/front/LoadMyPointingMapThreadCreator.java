package org.greatfree.framework.cps.cache.coordinator.front;

import org.greatfree.concurrency.reactive.RequestQueueCreator;
import org.greatfree.framework.cps.cache.message.front.LoadMyPointingMapRequest;
import org.greatfree.framework.cps.cache.message.front.LoadMyPointingMapResponse;
import org.greatfree.framework.cps.cache.message.front.LoadMyPointingMapStream;

// Created: 07/20/2018, Bing Li
public class LoadMyPointingMapThreadCreator implements RequestQueueCreator<LoadMyPointingMapRequest, LoadMyPointingMapStream, LoadMyPointingMapResponse, LoadMyPointingMapThread>
{

	@Override
	public LoadMyPointingMapThread createInstance(int taskSize)
	{
		return new LoadMyPointingMapThread(taskSize);
	}

}
