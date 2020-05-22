package quests;

import l2s.gameserver.listener.actor.player.OnLevelChangeListener;
import l2s.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.listener.CharListenerList;
import l2s.gameserver.model.base.NobleType;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;
import l2s.gameserver.network.l2.s2c.ExQuestNpcLogList;


public class _10823_ExaltedOneWhoShattersTheLimit extends Quest
{
	private static class PlayerEnterListener implements OnPlayerEnterListener
	{
		@Override
		public void onPlayerEnter(Player player)
		{
			QuestState questState = player.getQuestState(10823);
			if(questState == null)
				return;

			if(questState.getCond() == 1 && questState.get("CUSTOM_LOG") == null)
			{
				player.addListener(LEVEL_CHANGE_LISTENER);
			}
		}
	}

	private static class ChangeLevelListener implements OnLevelChangeListener
	{
		@Override
		public void onLevelChange(Player player, int was, int set)
		{
			QuestState questState = player.getQuestState(10823);
			if(questState == null)
				return;

			if(player.isDualClassActive() && set >= 100)
			{
				questState.set("CUSTOM_LOG", 1);
				player.sendPacket(new ExQuestNpcLogList(questState));
				if(questState.haveQuestItem(46056) && questState.haveQuestItem(46057) && questState.haveQuestItem(45635) && questState.haveQuestItem(45636))
				{
					questState.setCond(2);
					player.removeListener(LEVEL_CHANGE_LISTENER);
				}
			}
		}
	}

	// NPC's
	private static final int LEONEL = 33907;
	private static final OnPlayerEnterListener PLAYER_ENTER_LISTENER = new PlayerEnterListener();
	private static final OnLevelChangeListener LEVEL_CHANGE_LISTENER = new ChangeLevelListener();

	//Items
	private static final int NOBLESSCERTIF = 45638;
	private static final int SPELLBOOK = 45924;

	private static final int THIRDLIST = 45637;

	public _10823_ExaltedOneWhoShattersTheLimit()
	{
		super(PARTY_ONE, ONETIME);
		addStartNpc(LEONEL);
		addTalkId(LEONEL);
		addLevelCheck("lionel_hunter_q10823_02.htm", 99);
		addNobleCheck("lionel_hunter_q10823_02.htm", true);
		addQuestCompletedCheck("lionel_hunter_q10823_02.htm", 10811);
		addQuestCompletedCheck("lionel_hunter_q10823_02.htm", 10817);
		addCustomLog(1,"CUSTOM_LOG" , 582311, 1);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if (event.equalsIgnoreCase("lionel_hunter_q10823_05.htm"))
		{
			if (!st.haveQuestItem(THIRDLIST))
				st.giveItems(THIRDLIST, 1, false);
			st.setCond(1);
			st.getPlayer().addListener(LEVEL_CHANGE_LISTENER);
			if (st.getPlayer().isDualClassActive() && st.getPlayer().getLevel() >= 100)
				st.set("CUSTOM_LOG", 1);
		}
		else if (event.equalsIgnoreCase("lionel_hunter_q10823_08.htm"))
		{
			st.giveItems(NOBLESSCERTIF, 1, false);
			st.giveItems(SPELLBOOK, 1, false);
			st.takeItems(45637, -1);
			st.takeItems(46056, -1);
			st.takeItems(46057, -1);
			st.takeItems(45635, -1);
			st.takeItems(45636, -1);
			st.getPlayer().setNobleType(NobleType.HONORABLE);
			st.finishQuest();
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		int cond = st.getCond();

		String htmltext = NO_QUEST_DIALOG;

		switch (npcId)
		{
			case LEONEL:
				if (cond == 0)
					htmltext = "lionel_hunter_q10823_01.htm";
				else if (cond == 1 && !checkReward(st))
					htmltext = "lionel_hunter_q10823_06.htm";
				else if (cond == 2)
					htmltext = "lionel_hunter_q10823_07.htm";
				break;
		}
		return htmltext;
	}

	public boolean checkReward(QuestState st)
	{
		if (st.getPlayer().getDualClassLevel() > 99 && st.haveQuestItem(46056) && st.haveQuestItem(46057) && st.haveQuestItem(45635) && st.haveQuestItem(45636))
		{
			if (st.getCond() == 1)
			{
				st.setCond(2);
				st.getPlayer().removeListener(LEVEL_CHANGE_LISTENER);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onInit()
	{
		super.onInit();
		CharListenerList.addGlobal(PLAYER_ENTER_LISTENER);
	}
}