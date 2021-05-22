package org.greatfree.framework.cs.multinode.server;

import org.greatfree.chat.message.ShutdownServerNotification;
import org.greatfree.concurrency.reactive.NotificationQueueCreator;

// Created: 04/18/2017, Bing Li
class ShutdownChattingServerThreadCreator implements NotificationQueueCreator<ShutdownServerNotification, ShutdownChattingServerThread>
{

	@Override
	public ShutdownChattingServerThread createInstance(int taskSize)
	{
		return new ShutdownChattingServerThread(taskSize);
	}

}
