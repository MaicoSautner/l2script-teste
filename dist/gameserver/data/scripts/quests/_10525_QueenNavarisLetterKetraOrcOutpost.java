package quests;

import l2s.gameserver.listener.actor.player.OnLevelChangeListener;
import l2s.gameserver.listener.actor.player.OnPlayerEnterListener;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.actor.listener.CharListenerList;
import l2s.gameserver.model.base.ClassType;
import l2s.gameserver.model.base.Race;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

//By Evil_dnk

public class _10525_QueenNavarisLetterKetraOrcOutpost extends Quest
{
	// NPC's
	private static final int GREGORI = 31279;
	private static final int LUKONES = 33852;

	private static final int EXP_REWARD = 1277640;
	private static final int SP_REWARD = 306;

	private static final OnPlayerEnterListener PLAYER_ENTER_LISTENER = new PlayerEnterListener();
	private static final OnLevelChangeListener LEVEL_UP_LISTENER = new ChangeLevelListener();

	private static class PlayerEnterListener implements OnPlayerEnterListener
	{
		@Override
		public void onPlayerEnter(Player player)
		{
			QuestState questState = player.getQuestState(10525);
			if(questState == null)
				return;

			if(player.isBaseClassActive() && player.getLevel() > 80)
			{
				if (!questState.isCompleted())
				{
					questState.abortQuest();
				}
			}
		}
	}

	private static class ChangeLevelListener implements OnLevelChangeListener
	{
		@Override
		public void onLevelChange(Player player, int was, int set)
		{
			QuestState questState = player.getQuestState(10525);
			if(questState == null)
				return;

			if(player.isBaseClassActive() && player.getLevel() > 80)
			{
				if (!questState.isCompleted())
				{
					questState.abortQuest();
				}
			}
		}
	}

	@Override
	public void onInit()
	{
		super.onInit();
		CharListenerList.addGlobal(PLAYER_ENTER_LISTENER);
		CharListenerList.addGlobal(LEVEL_UP_LISTENER);
	}

	public _10525_QueenNavarisLetterKetraOrcOutpost()
	{
		super(PARTY_NONE, ONETIME, false);
		addTalkId(GREGORI, LUKONES);
		addRaceCheck(NO_QUEST_DIALOG, Race.ERTHEIA);
		addClassIdCheck(NO_QUEST_DIALOG, 185, 187);
		addLevelCheck(NO_QUEST_DIALOG, 76, 80);
		addClassTypeCheck(NO_QUEST_DIALOG, ClassType.MYSTIC);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("highpriest_gregor_q10525_03.htm"))
		{
			st.giveItems(46732, 1, false);
			st.setCond(3);
		}

		else if(event.equalsIgnoreCase("rugoness_q10525_01.htm"))
		{
			st.addExpAndSp(EXP_REWARD, SP_REWARD);
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
			case GREGORI:
				if(cond == 2)
					htmltext = "highpriest_gregor_q10525_01.htm";
				else if(cond == 3)
					htmltext = "highpriest_gregor_q10525_04.htm";
				break;

			case LUKONES:
				if (cond == 3)
					htmltext = "rugoness_q10525_01.htm";
				break;
		}
		return htmltext;
	}
}
