package org.greatfree.app.cs.twonode.server;

import java.util.Calendar;

import org.greatfree.app.cs.twonode.message.BusinessMessageType;
import org.greatfree.app.cs.twonode.message.ShutdownBusinessServerNotification;
import org.greatfree.client.MessageStream;
import org.greatfree.concurrency.reactive.NotificationDispatcher;
import org.greatfree.data.ServerConfig;
import org.greatfree.message.ServerMessage;
import org.greatfree.server.ServerDispatcher;

// Created: 07/25/2018, Bing Li
class BusinessManServerDispatcher extends ServerDispatcher<ServerMessage>
{
	private NotificationDispatcher<ShutdownBusinessServerNotification, ShutdownBusinessServerThread, ShutdownBusinessServerThreadCreator> shutdownNotificationDispatcher;

//	public BusinessManServerDispatcher(int schedulerPoolSize, long schedulerKeepAliveTime)
	public BusinessManServerDispatcher(int threadPoolSize, long threadKeepAliveTime, int schedulerPoolSize, long schedulerKeepAliveTime)
	{
		super(threadPoolSize, threadKeepAliveTime, schedulerPoolSize, schedulerKeepAliveTime);
//		super(schedulerPoolSize, schedulerKeepAliveTime);

		this.shutdownNotificationDispatcher = new NotificationDispatcher.NotificationDispatcherBuilder<ShutdownBusinessServerNotification, ShutdownBusinessServerThread, ShutdownBusinessServerThreadCreator>()
				.poolSize(ServerConfig.NOTIFICATION_DISPATCHER_POOL_SIZE)
				.threadCreator(new ShutdownBusinessServerThreadCreator())
				.notificationQueueSize(ServerConfig.NOTIFICATION_QUEUE_SIZE)
				.dispatcherWaitTime(ServerConfig.NOTIFICATION_DISPATCHER_WAIT_TIME)
				.waitRound(ServerConfig.NOTIFICATION_DISPATCHER_WAIT_ROUND)
				.idleCheckDelay(ServerConfig.NOTIFICATION_DISPATCHER_IDLE_CHECK_DELAY)
				.idleCheckPeriod(ServerConfig.NOTIFICATION_DISPATCHER_IDLE_CHECK_PERIOD)
				.scheduler(super.getScheduler())
				.build();
	}

	/*
	public void shutdown(long timeout) throws InterruptedException
	{
		this.shutdownNotificationDispatcher.dispose();
		super.shutdown(timeout);
	}

	public void consume(OutMessageStream<ServerMessage> message)
	{
		// Check the types of received messages. 04/17/2017, Bing Li
		switch (message.getMessage().getType())
		{	
			case BusinessMessageType.SHUTDOWN_BUSINESS_SERVER_NOTIFICATION:
				System.out.println("SHUTDOWN_BUSINESS_SERVER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				// Check whether the shutdown notification dispatcher is ready or not. 02/15/2016, Bing Li
				if (!this.shutdownNotificationDispatcher.isReady())
				{
					// Execute the notification dispatcher concurrently. 02/15/2016, Bing Li
					super.execute(this.shutdownNotificationDispatcher);
				}
				// Enqueue the instance of ShutdownChatServerNotification into the dispatcher for concurrent processing. 02/15/2016, Bing Li
				this.shutdownNotificationDispatcher.enqueue((ShutdownBusinessServerNotification)message.getMessage());
				break;
		}
	}
	*/

	@Override
	public void dispose(long timeout) throws InterruptedException
	{
		super.shutdown(timeout);
		this.shutdownNotificationDispatcher.dispose();
	}

	@Override
	public void process(MessageStream<ServerMessage> message)
	{
		// Check the types of received messages. 04/17/2017, Bing Li
		switch (message.getMessage().getType())
		{	
			case BusinessMessageType.SHUTDOWN_BUSINESS_SERVER_NOTIFICATION:
				System.out.println("SHUTDOWN_BUSINESS_SERVER_NOTIFICATION received @" + Calendar.getInstance().getTime());
				// Check whether the shutdown notification dispatcher is ready or not. 02/15/2016, Bing Li
				if (!this.shutdownNotificationDispatcher.isReady())
				{
					// Execute the notification dispatcher concurrently. 02/15/2016, Bing Li
					super.execute(this.shutdownNotificationDispatcher);
				}
				// Enqueue the instance of ShutdownChatServerNotification into the dispatcher for concurrent processing. 02/15/2016, Bing Li
				this.shutdownNotificationDispatcher.enqueue((ShutdownBusinessServerNotification)message.getMessage());
				break;
		}
	}

}
