package edu.greatfree.p2p.registry;

import org.greatfree.concurrency.reactive.RequestThreadCreatable;
import org.greatfree.dip.multicast.message.PeerAddressRequest;
import org.greatfree.dip.multicast.message.PeerAddressResponse;
import org.greatfree.dip.multicast.message.PeerAddressStream;

// Created: 09/11/2018, Bing Li
class PeerAddressRequestThreadCreator implements RequestThreadCreatable<PeerAddressRequest, PeerAddressStream, PeerAddressResponse, PeerAddressRequestThread>
{

	@Override
	public PeerAddressRequestThread createRequestThreadInstance(int taskSize)
	{
		return new PeerAddressRequestThread(taskSize);
	}

}
