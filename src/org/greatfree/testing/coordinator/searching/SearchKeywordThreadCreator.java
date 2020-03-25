package org.greatfree.testing.coordinator.searching;

import org.greatfree.concurrency.reactive.RequestThreadCreatable;
import org.greatfree.testing.message.SearchKeywordRequest;
import org.greatfree.testing.message.SearchKeywordResponse;
import org.greatfree.testing.message.SearchKeywordStream;

/*
 * A creator to initialize instances of SearchKeywordThread. It is used in the instance of RequestDispatcher. 11/29/2014, Bing Li
 */

// Created: 11/29/2014, Bing Li
public class SearchKeywordThreadCreator implements RequestThreadCreatable<SearchKeywordRequest, SearchKeywordStream, SearchKeywordResponse, SearchKeywordThread>
{
	@Override
	public SearchKeywordThread createRequestThreadInstance(int taskSize)
	{
		return new SearchKeywordThread(taskSize);
	}
}
