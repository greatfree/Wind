package org.greatfree.framework.cluster.cs.twonode.clusterserver.child;

import java.io.IOException;

import org.greatfree.data.ServerConfig;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.util.TerminateSignal;

// Created: 01/13/2019, Bing Li
class StartChild
{

	public static void main(String[] args)
	{
		System.out.println("Chatting child starting up ...");

		try
		{
			ChatChild.CONTAINER().start(new ChatTask());
		}
		catch (ClassNotFoundException | IOException | RemoteReadException | InterruptedException e)
		{
			e.printStackTrace();
		}

		System.out.println("Chatting child started ...");

		// After the server is started, the loop check whether the flag of terminating is set. If the terminating flag is true, the process is ended. Otherwise, the process keeps running. 08/22/2014, Bing Li
		while (!TerminateSignal.SIGNAL().isTerminated())
		{
			try
			{
				// If the terminating flag is false, it is required to sleep for some time. Otherwise, it might cause the high CPU usage. 08/22/2014, Bing Li
				Thread.sleep(ServerConfig.TERMINATE_SLEEP);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
