package ai;

import l2s.commons.threading.RunnableImpl;
import l2s.gameserver.ThreadPoolManager;
import l2s.gameserver.ai.DefaultAI;
import l2s.gameserver.data.xml.holder.SkillHolder;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.instances.NpcInstance;

/**
 * @author pchayka
 *
 * Не факт, что работает полностью верно. Судя по описнию тотем производит ауру, а не бафает.
 */
public class TotemSummon extends DefaultAI
{
	private static final int TotemofBody = 143;
	private static final int TotemofSpirit = 144;
	private static final int TotemofBravery = 145;
	private static final int TotemofFortitude = 146;

	private static final int TotemofBodyBuff = 23308;
	private static final int TotemofSpiritBuff = 23309;
	private static final int TotemofBraveryBuff = 23310;
	private static final int TotemofFortitudeBuff = 23311;
	private long _timer = 0;

	public TotemSummon(NpcInstance actor)
	{
		super(actor);
		actor.setHasChatWindow(false);
		actor.getFlags().getImmobilized().start();
	}

	@Override
	protected void onEvtSpawn()
	{
		super.onEvtSpawn();
		ThreadPoolManager.getInstance().schedule(() ->
		{
			if(getActor() != null)
				getActor().deleteMe();
		}, 30 * 60 * 1000L);
	}

	@Override
	protected boolean thinkActive()
	{
		if(_timer < System.currentTimeMillis())
		{
			_timer = System.currentTimeMillis() + 15000L;
			for(Creature c : getActor().getAroundCharacters(450, 200))
				if(c.isPlayable() && !c.isDead())
					c.altOnMagicUse(c, SkillHolder.getInstance().getSkill(getBuffId(getActor().getNpcId()), 1));
		}

		return true;
	}

	private int getBuffId(int npcId)
	{
		int buffId = 0;
		switch(npcId)
		{
			case TotemofBody:
				buffId = TotemofBodyBuff;
				break;
			case TotemofSpirit:
				buffId = TotemofSpiritBuff;
				break;
			case TotemofBravery:
				buffId = TotemofBraveryBuff;
				break;
			case TotemofFortitude:
				buffId = TotemofFortitudeBuff;
				break;
			default:
				break;
		}
		return buffId;
	}
}