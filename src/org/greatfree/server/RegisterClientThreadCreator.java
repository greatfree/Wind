package org.greatfree.server;

import org.greatfree.concurrency.reactive.NotificationThreadCreatable;
import org.greatfree.testing.message.RegisterClientNotification;

/*
 * The code here attempts to create instances of SetInputStreamThread. 11/30/2014, Bing Li
 */

// Created: 11/30/2014, Bing Li
public class RegisterClientThreadCreator implements NotificationThreadCreatable<RegisterClientNotification, RegisterClientThread>
{
	@Override
	public RegisterClientThread createNotificationThreadInstance(int taskSize)
	{
		return new RegisterClientThread(taskSize);
	}
}
