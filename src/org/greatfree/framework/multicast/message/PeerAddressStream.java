package org.greatfree.framework.multicast.message;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.MessageStream;

// Created: 09/11/2018, Bing Li
public class PeerAddressStream extends MessageStream<PeerAddressRequest>
{

	public PeerAddressStream(ObjectOutputStream out, Lock lock, PeerAddressRequest message)
	{
		super(out, lock, message);
		// TODO Auto-generated constructor stub
	}

}
