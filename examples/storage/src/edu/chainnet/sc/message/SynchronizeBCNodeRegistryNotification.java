package edu.chainnet.sc.message;

import org.greatfree.message.multicast.container.Notification;

/*
 * When BC nodes information is updated, the notification is used to synchronize that between the chains and the collaborator. 10/18/2020, Bing Li
 */

// Created: 10/18/2020, Bing Li
public class SynchronizeBCNodeRegistryNotification extends Notification
{
	private static final long serialVersionUID = -6441352755471874103L;

	private BCNode node;

	public SynchronizeBCNodeRegistryNotification(BCNode node)
	{
		super(node.getKey(), SCAppID.SYNCHRONIZE_BLOCK_CHAIN_NODE_REGISTRY_NOTIFICATION);
		this.node = node;
	}

	public BCNode getNode()
	{
		return this.node;
	}
}
