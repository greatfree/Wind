package org.greatfree.app.business.dip.cs.multinode.server;

import org.greatfree.chat.message.cs.business.CheckMerchandiseRequest;
import org.greatfree.chat.message.cs.business.CheckMerchandiseResponse;
import org.greatfree.chat.message.cs.business.CheckMerchandiseStream;
import org.greatfree.concurrency.reactive.RequestThreadCreatable;

// Created: 12/05/2017, Bing Li
public class CheckMerchandiseThreadCreator implements RequestThreadCreatable<CheckMerchandiseRequest, CheckMerchandiseStream, CheckMerchandiseResponse, CheckMerchandiseThread>
{

	@Override
	public CheckMerchandiseThread createRequestThreadInstance(int taskSize)
	{
		return new CheckMerchandiseThread(taskSize);
	}

}
