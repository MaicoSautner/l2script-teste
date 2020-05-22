package ai.residences.fortress.siege;

import l2s.gameserver.data.xml.holder.SkillHolder;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.entity.events.impl.FortressSiegeEvent;
import l2s.gameserver.model.entity.residence.Fortress;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.network.l2.components.NpcString;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.utils.Functions;

import ai.residences.SiegeGuardFighter;

/**
 * @author VISTALL
 * @date 16:43/17.04.2011
 */
public class GuardCaption extends SiegeGuardFighter
{
	public GuardCaption(NpcInstance actor)
	{
		super(actor);
	}

	@Override
	protected void onEvtSpawn()
	{
		super.onEvtSpawn();
		NpcInstance actor = getActor();

		FortressSiegeEvent siegeEvent = actor.getEvent(FortressSiegeEvent.class);
		if(siegeEvent == null)
			return;

		if(siegeEvent.getResidence().getFacilityLevel(Fortress.GUARD_BUFF) > 0)
			actor.doCast(SkillHolder.getInstance().getSkillEntry(5432, siegeEvent.getResidence().getFacilityLevel(Fortress.GUARD_BUFF)), actor, false);

		siegeEvent.barrackAction(1, false);
	}

	@Override
	protected void onEvtDead(Creature killer)
	{
		NpcInstance actor = getActor();
		FortressSiegeEvent siegeEvent = actor.getEvent(FortressSiegeEvent.class);
		if(siegeEvent == null)
			return;

		siegeEvent.barrackAction(1, true);

		siegeEvent.broadcastTo(SystemMsg.THE_BARRACKS_HAVE_BEEN_SEIZED, FortressSiegeEvent.ATTACKERS, FortressSiegeEvent.DEFENDERS);

		Functions.npcShout(actor, NpcString.AIIEEEE_COMMAND_CENTER_THIS_IS_GUARD_UNIT_WE_NEED_BACKUP_RIGHT_AWAY);

		super.onEvtDead(killer);

		siegeEvent.checkBarracks();
	}
}
