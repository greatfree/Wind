package org.greatfree.cache.message.child;

import java.util.HashMap;

import org.greatfree.cache.message.BroadValuesRequest;
import org.greatfree.message.ChildBroadcastRequestCreatable;
import org.greatfree.util.IPAddress;

/*
 * The creator generates request of BroadValuesRequest that should be broadcast to the current distributed node's children. 07/14/2017, Bing Li
 */

// Created: 07/14/2017, Bing Li
public class BroadValuesRequestCreator implements ChildBroadcastRequestCreatable<BroadValuesRequest>
{

	@Override
	public BroadValuesRequest createInstanceWithChildren(HashMap<String, IPAddress> children, BroadValuesRequest msg)
	{
//		return new BroadValuesRequest(Tools.generateUniqueKey(), msg.getCollaboratorKey(), children);
		return new BroadValuesRequest(children);
	}

}
