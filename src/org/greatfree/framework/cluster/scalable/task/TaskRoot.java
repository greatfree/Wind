package org.greatfree.framework.cluster.scalable.task;

import java.io.IOException;

import org.greatfree.cluster.RootTask;
import org.greatfree.cluster.root.container.ClusterServerContainer;
import org.greatfree.exceptions.DistributedNodeFailedException;
import org.greatfree.exceptions.DuplicatePeerNameException;
import org.greatfree.exceptions.RemoteIPNotExistedException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.exceptions.ServerPortConflictedException;
import org.greatfree.framework.cluster.scalable.ScalableConfig;
import org.greatfree.util.TerminateSignal;

/*
 * The code is a normal cluster to accomplish distributed tasks. It needs to increase or decrease its scale upon its workload. 09/06/2020, Bing Li
 */

// Created: 09/06/2020, Bing Li
class TaskRoot
{
	private ClusterServerContainer server;

	private TaskRoot()
	{
	}
	
	private static TaskRoot instance = new TaskRoot();
	
	public static TaskRoot TASK()
	{
		if (instance == null)
		{
			instance = new TaskRoot();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	
	public void stopCluster() throws IOException, DistributedNodeFailedException
	{
		this.server.stopCluster();
	}

	public void stopServer(long timeout) throws ClassNotFoundException, IOException, InterruptedException, RemoteReadException, RemoteIPNotExistedException
	{
		TerminateSignal.SIGNAL().notifyAllTermination();
		this.server.stop(timeout);
	}
	
	public void start(int port, RootTask task) throws IOException, ClassNotFoundException, RemoteReadException, DistributedNodeFailedException, DuplicatePeerNameException, RemoteIPNotExistedException, ServerPortConflictedException
	{
		this.server = new ClusterServerContainer(port, ScalableConfig.TASK_CLUSTER_ROOT, task);
		this.server.start();
	}

}
