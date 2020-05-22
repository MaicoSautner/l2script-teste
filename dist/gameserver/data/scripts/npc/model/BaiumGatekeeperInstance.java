package npc.model;

import l2s.commons.collections.MultiValueSet;
import l2s.gameserver.model.GameObjectsStorage;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.templates.npc.NpcTemplate;
//import l2s.gameserver.utils.ItemFunctions;
import l2s.gameserver.utils.Location;

import bosses.BaiumManager;

/**
 * @author Bonux
 */
public final class BaiumGatekeeperInstance extends NpcInstance
{
	private static final long serialVersionUID = 1L;

	// NPC's
	private static final int BAIUM_RAID_NPC_ID = 29020;
	private static final int BAIUM_STONED_NPC_ID = 29025;

	// Locations
	private static final Location TELEPORT_POSITION = new Location(113100, 14500, 10077);

	public BaiumGatekeeperInstance(int objectId, NpcTemplate template, MultiValueSet<String> set)
	{
		super(objectId, template, set);
	}

	@Override
	public void onTeleportRequest(Player talker)
	{
		NpcInstance baiumNpc = GameObjectsStorage.getByNpcId(BAIUM_STONED_NPC_ID);
		NpcInstance baiumBoss = GameObjectsStorage.getByNpcId(BAIUM_RAID_NPC_ID);
		if(baiumNpc != null || baiumBoss != null)
		{
			if(baiumBoss == null)
			{
				if(BaiumManager.consumeRequiredItems(talker))
				{
					talker.setVar("baiumPermission", "granted", -1);
					talker.teleToLocation(TELEPORT_POSITION);
				}
				else
					showChatWindow(talker, "default/dimension_vertex_4002.htm", false);
			}
			else
				showChatWindow(talker, "default/dimension_vertex_4003.htm", false);
		}
		else
			showChatWindow(talker, "default/dimension_vertex_4004.htm", false);
	}

	@Override
	public void showChatWindow(Player player, int val, boolean firstTalk, Object... arg)
	{
		if(val == 0)
			showChatWindow(player, "default/dimension_vertex_4001.htm", firstTalk);
		else
			super.showChatWindow(player, val, firstTalk, arg);
	}
}