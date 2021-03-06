package org.greatfree.framework.cps.cache.message.prefetch;

import java.io.ObjectOutputStream;
import java.util.concurrent.locks.Lock;

import org.greatfree.client.MessageStream;

// Created: 02/25/2019, Bing Li
public class PrefetchMyUKValuesStream extends MessageStream<PrefetchMyUKValuesRequest>
{

	public PrefetchMyUKValuesStream(ObjectOutputStream out, Lock lock, PrefetchMyUKValuesRequest message)
	{
		super(out, lock, message);
	}

}
