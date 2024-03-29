package ca.streaming.news.publisher;

import java.io.IOException;

import org.greatfree.client.CSClient;
import org.greatfree.data.ClientConfig;
import org.greatfree.dip.multicast.MulticastConfig;
import org.greatfree.dip.multicast.message.PeerAddressRequest;
import org.greatfree.dip.multicast.message.PeerAddressResponse;
import org.greatfree.dip.p2p.RegistryConfig;
import org.greatfree.dip.streaming.StreamConfig;
import org.greatfree.dip.streaming.message.AddStreamNotification;
import org.greatfree.dip.streaming.message.RemoveStreamNotification;
import org.greatfree.exceptions.RemoteReadException;
import org.greatfree.message.multicast.MulticastMessage;
import org.greatfree.util.IPAddress;
import org.greatfree.util.Tools;

// Created: 04/02/2020, Bing Li
class Publisher
{
	private CSClient client;

	private String publisherName;
	private IPAddress rootAddress;
	private IPAddress pubSubAddress;
	
	private Publisher()
	{
	}

	private static Publisher instance = new Publisher();
	
	public static Publisher  CLIENT()
	{
		if (instance == null)
		{
			instance = new Publisher();
			return instance;
		}
		else
		{
			return instance;
		}
	}

	public void dispose() throws IOException, InterruptedException
	{
		this.client.dispose();
	}
	
	public void init(String publisherName) throws ClassNotFoundException, RemoteReadException, IOException
	{
		this.client = new CSClient.CSClientBuilder()
				.freeClientPoolSize(RegistryConfig.CLIENT_POOL_SIZE)
				.clientIdleCheckDelay(RegistryConfig.SYNC_EVENTER_IDLE_CHECK_DELAY)
				.clientIdleCheckPeriod(RegistryConfig.SYNC_EVENTER_IDLE_CHECK_PERIOD)
				.clientMaxIdleTime(RegistryConfig.SYNC_EVENTER_MAX_IDLE_TIME)
				.asyncEventQueueSize(RegistryConfig.ASYNC_EVENT_QUEUE_SIZE)
				.asyncEventerSize(RegistryConfig.ASYNC_EVENTER_SIZE)
				.asyncEventingWaitTime(RegistryConfig.ASYNC_EVENTING_WAIT_TIME)
				.asyncEventerWaitTime(RegistryConfig.ASYNC_EVENTER_WAIT_TIME)
				.asyncEventerWaitRound(RegistryConfig.ASYNC_EVENTER_WAIT_ROUND)
				.asyncEventIdleCheckDelay(RegistryConfig.ASYNC_EVENT_IDLE_CHECK_DELAY)
				.asyncEventIdleCheckPeriod(RegistryConfig.ASYNC_EVENT_IDLE_CHECK_PERIOD)
				.schedulerPoolSize(RegistryConfig.SCHEDULER_THREAD_POOL_SIZE)
				.schedulerKeepAliveTime(RegistryConfig.SCHEDULER_THREAD_POOL_KEEP_ALIVE_TIME)
				.asyncSchedulerShutdownTimeout(ClientConfig.ASYNC_SCHEDULER_SHUTDOWN_TIMEOUT)
				.readerClientSize(RegistryConfig.READER_CLIENT_SIZE)
				.build();
		
		this.publisherName = publisherName;
		
		PeerAddressResponse response = (PeerAddressResponse)this.client.read(RegistryConfig.PEER_REGISTRY_ADDRESS,  RegistryConfig.PEER_REGISTRY_PORT, new PeerAddressRequest(Tools.getHash(MulticastConfig.CLUSTER_SERVER_ROOT_NAME)));
		this.rootAddress = response.getPeerAddress();
		
		response = (PeerAddressResponse)this.client.read(RegistryConfig.PEER_REGISTRY_ADDRESS,  RegistryConfig.PEER_REGISTRY_PORT, new PeerAddressRequest(Tools.getHash(StreamConfig.PUBSUB_SERVER_NAME)));
		this.pubSubAddress = response.getPeerAddress();
	}

	public void addStream(String topic) throws IOException, InterruptedException
	{
		this.client.syncNotify(this.pubSubAddress.getIP(), this.pubSubAddress.getPort(), new AddStreamNotification(this.publisherName, topic));
	}
	
	public void removeStream(String topic) throws IOException, InterruptedException
	{
		this.client.syncNotify(this.pubSubAddress.getIP(), this.pubSubAddress.getPort(), new RemoveStreamNotification(this.publisherName, topic));
	}

	public void publishStream(MulticastMessage message) throws IOException, InterruptedException
	{
		this.client.syncNotify(this.rootAddress.getIP(), this.rootAddress.getPort(), message);
	}

}
