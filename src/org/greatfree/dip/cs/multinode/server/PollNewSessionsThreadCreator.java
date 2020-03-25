package org.greatfree.dip.cs.multinode.server;

import org.greatfree.concurrency.reactive.RequestThreadCreatable;
import org.greatfree.dip.cs.multinode.message.PollNewSessionsRequest;
import org.greatfree.dip.cs.multinode.message.PollNewSessionsResponse;
import org.greatfree.dip.cs.multinode.message.PollNewSessionsStream;

// Created: 04/24/2017, Bing Li
class PollNewSessionsThreadCreator implements RequestThreadCreatable<PollNewSessionsRequest, PollNewSessionsStream, PollNewSessionsResponse, PollNewSessionsThread>
{

	@Override
	public PollNewSessionsThread createRequestThreadInstance(int taskSize)
	{
		return new PollNewSessionsThread(taskSize);
	}

}
