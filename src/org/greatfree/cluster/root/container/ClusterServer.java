package org.greatfree.cluster.root.container;

import java.io.IOException;

import org.greatfree.cluster.ClusterConfig;
import org.greatfree.cluster.RootTask;
import org.greatfree.concurrency.ThreadPool;
import org.greatfree.exceptions.DistributedNodeFailedException;
import org.greatfree.exceptions.DuplicatePeerNameException;
import org.greatfree.exceptions.RemoteIPNotExistedException;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.exceptions.ServerPortConflictedException;
import org.greatfree.framework.cluster.original.cs.twonode.message.StopChatClusterNotification;
import org.greatfree.server.container.Peer.PeerBuilder;
import org.greatfree.util.Builder;

// Created: 01/13/2019, Bing Li
final class ClusterServer
{
	public ClusterServer(ServerOnClusterBuilder builder) throws ServerPortConflictedException, IOException
	{
		PeerBuilder<RootDispatcher> peerBuilder = new PeerBuilder<RootDispatcher>();
		
		peerBuilder.peerPort(builder.getPeerPort())
			.peerName(builder.getPeerName())
			.registryServerIP(builder.getRegistryServerIP())
			.registryServerPort(builder.getRegistryServerPort())
			.isRegistryNeeded(true)
			.listenerCount(builder.getListenerCount())
			.maxIOCount(builder.getMaxIOCount())
//			.serverThreadPoolSize(builder.getServerThreadPoolSize())
//			.serverThreadKeepAliveTime(builder.getServerThreadKeepAliveTime())
			.dispatcher(new RootDispatcher(builder.getServerThreadPoolSize(), builder.getServerThreadKeepAliveTime(), builder.getSchedulerPoolSize(), builder.getSchedulerKeepAliveTime()))
			.freeClientPoolSize(builder.getClientPoolSize())
			.readerClientSize(builder.getReaderClientSize())
			.syncEventerIdleCheckDelay(builder.getClientIdleCheckDelay())
			.syncEventerIdleCheckPeriod(builder.getClientIdleCheckPeriod())
			.syncEventerMaxIdleTime(builder.getClientMaxIdleTime())
			.asyncEventQueueSize(builder.getAsyncEventQueueSize())
			.asyncEventerSize(builder.getAsyncEventerSize())
			.asyncEventingWaitTime(builder.getAsyncEventingWaitTime())
			.asyncEventQueueWaitTime(builder.getAsyncEventQueueWaitTime())
//			.asyncEventerWaitRound(builder.getAsyncEventerWaitRound())
			.asyncEventIdleCheckDelay(builder.getAsyncEventIdleCheckDelay())
			.asyncEventIdleCheckPeriod(builder.getAsyncEventIdleCheckPeriod())
			.isBroker(builder.isBroker())
			.schedulerPoolSize(builder.getSchedulerPoolSize())
			.schedulerKeepAliveTime(builder.getSchedulerKeepAliveTime());

		if (builder.getReplicas() == ClusterConfig.NO_REPLICAS)
		{
			ClusterRoot.CONTAINER().init(peerBuilder, builder.getRootBranchCount(), builder.getTreeBranchCount(), builder.getRequestWaitTime());
		}
		else
		{
			ClusterRoot.CONTAINER().init(peerBuilder, builder.getRootBranchCount(), builder.getTreeBranchCount(), builder.getRequestWaitTime(), builder.getReplicas());
		}
	}
	
	public static class ServerOnClusterBuilder implements Builder<ClusterServer>
	{
		private String peerName;
		private int peerPort;
		private String registryServerIP;
		private int registryServerPort;
		private boolean isRegistryNeeded;
		private int listenerCount;
		private int maxIOCount;
		// The size of the thread pool that manages the threads of the server. 05/11/2017, Bing Li
		private int serverThreadPoolSize;
		// The time to keep alive for threads of the server. 05/11/2017, Bing Li
		private long serverThreadKeepAliveTime;
		
		private int clientPoolSize;
		private int readerClientSize;
		private long clientIdleCheckDelay;
		private long clientIdleCheckPeriod;
		private long clientMaxIdleTime;
		
		private int asyncEventQueueSize;
		private int asyncEventerSize;
		private long asyncEventingWaitTime;
		private long asyncEventQueueWaitTime;
//		private int asyncEventerWaitRound;
		private long asyncEventIdleCheckDelay;
		private long asyncEventIdleCheckPeriod;
		
		private int schedulerPoolSize;
		private long schedulerKeepAliveTime;
		
		private boolean isBroker = false;

		private int rootBranchCount;
		private int treeBranchCount;
		private long requestWaitTime;
		
		private int replicas;
		
		public ServerOnClusterBuilder()
		{
		}

		public ServerOnClusterBuilder peerName(String peerName)
		{
			this.peerName = peerName;
			return this;
		}

		public ServerOnClusterBuilder peerPort(int peerPort)
		{
			this.peerPort = peerPort;
			return this;
		}
		
		public ServerOnClusterBuilder registryServerIP(String registryServerIP)
		{
			this.registryServerIP = registryServerIP;
			return this;
		}
		
		public ServerOnClusterBuilder registryServerPort(int registryServerPort)
		{
			this.registryServerPort = registryServerPort;
			return this;
		}
		
		public ServerOnClusterBuilder isRegistryNeeded(boolean isRegistryNeeded)
		{
			this.isRegistryNeeded = isRegistryNeeded;
			return this;
		}

		public ServerOnClusterBuilder listenerCount(int listenerCount)
		{
			this.listenerCount = listenerCount;
			return this;
		}

		public ServerOnClusterBuilder maxIOCount(int maxIOCount)
		{
			this.maxIOCount = maxIOCount;
			return this;
		}

		public ServerOnClusterBuilder serverThreadPoolSize(int serverThreadPoolSize)
		{
			this.serverThreadPoolSize = serverThreadPoolSize;
			return this;
		}
		
		public ServerOnClusterBuilder serverThreadKeepAliveTime(long serverThreadKeepAliveTime)
		{
			this.serverThreadKeepAliveTime = serverThreadKeepAliveTime;
			return this;
		}

		public ServerOnClusterBuilder freeClientPoolSize(int clientPoolSize)
		{
			this.clientPoolSize = clientPoolSize;
			return this;
		}
		
		public ServerOnClusterBuilder readerClientSize(int readerClientSize)
		{
			this.readerClientSize = readerClientSize;
			return this;
		}
		
		public ServerOnClusterBuilder syncEventerIdleCheckDelay(long idleCheckDelay)
		{
			this.clientIdleCheckDelay = idleCheckDelay;
			return this;
		}
		
		public ServerOnClusterBuilder syncEventerIdleCheckPeriod(long idleCheckPeriod)
		{
			this.clientIdleCheckPeriod = idleCheckPeriod;
			return this;
		}

		public ServerOnClusterBuilder syncEventerMaxIdleTime(long maxIdleTime)
		{
			this.clientMaxIdleTime = maxIdleTime;
			return this;
		}

		public ServerOnClusterBuilder asyncEventQueueSize(int asyncEventQueueSize)
		{
			this.asyncEventQueueSize = asyncEventQueueSize;
			return this;
		}

		public ServerOnClusterBuilder asyncEventerSize(int asyncEventerSize)
		{
			this.asyncEventerSize = asyncEventerSize;
			return this;
		}

		public ServerOnClusterBuilder asyncEventingWaitTime(long asyncEventingWaitTime)
		{
			this.asyncEventingWaitTime = asyncEventingWaitTime;
			return this;
		}

		public ServerOnClusterBuilder asyncEventQueueWaitTime(long asyncEventQueueWaitTime)
		{
			this.asyncEventQueueWaitTime = asyncEventQueueWaitTime;
			return this;
		}

		/*
		public ServerOnClusterBuilder asyncEventerWaitRound(int asyncEventerWaitRound)
		{
			this.asyncEventerWaitRound = asyncEventerWaitRound;
			return this;
		}
		*/

		public ServerOnClusterBuilder asyncEventIdleCheckDelay(long asyncEventIdleCheckDelay)
		{
			this.asyncEventIdleCheckDelay = asyncEventIdleCheckDelay;
			return this;
		}

		public ServerOnClusterBuilder asyncEventIdleCheckPeriod(long asyncEventIdleCheckPeriod)
		{
			this.asyncEventIdleCheckPeriod = asyncEventIdleCheckPeriod;
			return this;
		}

		public ServerOnClusterBuilder schedulerPoolSize(int schedulerPoolSize)
		{
			this.schedulerPoolSize = schedulerPoolSize;
			return this;
		}

		public ServerOnClusterBuilder schedulerKeepAliveTime(long schedulerKeepAliveTime)
		{
			this.schedulerKeepAliveTime = schedulerKeepAliveTime;
			return this;
		}

		public ServerOnClusterBuilder isBroker(boolean isBroker)
		{
			this.isBroker = isBroker;
			return this;
		}
		
		public ServerOnClusterBuilder rootBranchCount(int rootBranchCount)
		{
			this.rootBranchCount = rootBranchCount;
			return this;
		}
		
		public ServerOnClusterBuilder treeBranchCount(int treeBranchCount)
		{
			this.treeBranchCount = treeBranchCount;
			return this;
		}
		
		public ServerOnClusterBuilder requestWaitTime(long requestWaitTime)
		{
			this.requestWaitTime = requestWaitTime;
			return this;
		}

		public ServerOnClusterBuilder replicas(int replicas)
		{
			this.replicas = replicas;
			return this;
		}

		@Override
		public ClusterServer build() throws IOException
		{
			try
			{
				return new ClusterServer(this);
			}
			catch (ServerPortConflictedException | IOException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		
		public String getPeerName()
		{
			return this.peerName;
		}
		
		public int getPeerPort()
		{
			return this.peerPort;
		}
		
		public String getRegistryServerIP()
		{
			return this.registryServerIP;
		}
		
		public int getRegistryServerPort()
		{
			return this.registryServerPort;
		}
		
		public boolean isRegistryNeeded()
		{
			return this.isRegistryNeeded;
		}
		
		public int getListenerCount()
		{
			return this.listenerCount;
		}
		
		public int getMaxIOCount()
		{
			return this.maxIOCount;
		}

		public int getServerThreadPoolSize()
		{
			return this.serverThreadPoolSize;
		}
		
		public long getServerThreadKeepAliveTime()
		{
			return this.serverThreadKeepAliveTime;
		}
		
		public int getClientPoolSize()
		{
			return this.clientPoolSize;
		}
		
		public int getReaderClientSize()
		{
			return this.readerClientSize;
		}
		
		public long getClientIdleCheckDelay()
		{
			return this.clientIdleCheckDelay;
		}
		
		public long getClientIdleCheckPeriod()
		{
			return this.clientIdleCheckPeriod;
		}
		
		public long getClientMaxIdleTime()
		{
			return this.clientMaxIdleTime;
		}
		
		public int getAsyncEventQueueSize()
		{
			return this.asyncEventQueueSize;
		}
		
		public int getAsyncEventerSize()
		{
			return this.asyncEventerSize;
		}
		
		public long getAsyncEventingWaitTime()
		{
			return this.asyncEventingWaitTime;
		}
		
		public long getAsyncEventQueueWaitTime()
		{
			return this.asyncEventQueueWaitTime;
		}

		/*
		public int getAsyncEventerWaitRound()
		{
			return this.asyncEventerWaitRound;
		}
		*/
		
		public long getAsyncEventIdleCheckDelay()
		{
			return this.asyncEventIdleCheckDelay;
		}
		
		public long getAsyncEventIdleCheckPeriod()
		{
			return this.asyncEventIdleCheckPeriod;
		}

		public int getSchedulerPoolSize()
		{
			return this.schedulerPoolSize;
		}
		
		public long getSchedulerKeepAliveTime()
		{
			return this.schedulerKeepAliveTime;
		}
		
		public boolean isBroker()
		{
			return this.isBroker;
		}

		public int getRootBranchCount()
		{
			return this.rootBranchCount;
		}
		
		public int getTreeBranchCount()
		{
			return this.treeBranchCount;
		}
		
		public long getRequestWaitTime()
		{
			return this.requestWaitTime;
		}
		
		public int getReplicas()
		{
			return this.replicas;
		}
	}
	
	/*
	 * For testing only. 01/16/2019, Bing Li
	 */
	/*
	public CSClient getPeerClient()
	{
		return ClusterRoot.CONTAINER().getPeerClient();
	}
	*/
	
	public ThreadPool getThreadPool()
	{
		return ClusterRoot.CONTAINER().getThreadPool();
	}

	public boolean isChildrenEmpty()
	{
		return ClusterRoot.CONTAINER().getChildrenCount() <= 0;
	}
	
	public void stopCluster() throws IOException, DistributedNodeFailedException
	{
		if (!this.isChildrenEmpty())
		{
			ClusterRoot.CONTAINER().broadcastNotify(new StopChatClusterNotification());
		}
		else
		{
			System.out.println("No children join!");
		}
	}
	
	public void stop(long timeout) throws ClassNotFoundException, InterruptedException, RemoteReadException, IOException, RemoteIPNotExistedException
	{
		ClusterRoot.CONTAINER().dispose(timeout);
	}

	public void start(RootTask task) throws ClassNotFoundException, RemoteReadException, DistributedNodeFailedException, DuplicatePeerNameException, RemoteIPNotExistedException, ServerPortConflictedException, IOException
	{
		RootServiceProvider.ROOT().init(task);
		ClusterRoot.CONTAINER().start();
	}
}
