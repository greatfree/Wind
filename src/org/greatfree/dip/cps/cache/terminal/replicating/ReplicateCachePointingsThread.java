package org.greatfree.dip.cps.cache.terminal.replicating;

import org.greatfree.concurrency.reactive.NotificationQueue;
import org.greatfree.data.ServerConfig;
import org.greatfree.dip.cps.cache.message.replicate.ReplicateCachePointingsNotification;
import org.greatfree.dip.cps.cache.terminal.MySortedTerminalListStore;
import org.greatfree.dip.cps.cache.terminal.MySortedTerminalMapStore;
import org.greatfree.dip.cps.cache.terminal.MyTimingTerminalMapStore;

// Created: 07/25/2018, Bing Li
public class ReplicateCachePointingsThread extends NotificationQueue<ReplicateCachePointingsNotification>
{

	public ReplicateCachePointingsThread(int taskSize)
	{
		super(taskSize);
	}

	@Override
	public void run()
	{
		ReplicateCachePointingsNotification notification;
		while (!this.isShutdown())
		{
			while (!this.isEmpty())
			{
				try
				{
					notification = this.getNotification();
					if (notification.getPointings() != null)
					{
						if (notification.isTerminalMap())
						{
							MySortedTerminalMapStore.BACKEND().putAll(notification.getMapKey(), notification.getPointings());
						}
						else
						{
							MySortedTerminalListStore.BACKEND().addAll(notification.getMapKey(), notification.getPointings());
						}
					}
					else
					{
						MyTimingTerminalMapStore.BACKEND().putAll(notification.getMapKey(), notification.getTimings());
					}
					this.disposeMessage(notification);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				this.holdOn(ServerConfig.NOTIFICATION_THREAD_WAIT_TIME);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
