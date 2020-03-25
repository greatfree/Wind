package org.greatfree.dip.multicast.root;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.dip.multicast.message.HelloWorldAnycastNotification;

// Created: 08/26/2018, Bing Li
class ClientHelloWorldAnycastNotificationThreadCreator implements NotificationThreadCreatable<HelloWorldAnycastNotification, ClientHelloWorldAnycastNotificationThread>
{

	@Override
	public ClientHelloWorldAnycastNotificationThread createNotificationThreadInstance(int taskSize)
	{
		return new ClientHelloWorldAnycastNotificationThread(taskSize);
	}

}
