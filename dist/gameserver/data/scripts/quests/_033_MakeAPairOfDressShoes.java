package quests;

import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public class _033_MakeAPairOfDressShoes extends Quest
{
	int LEATHER = 36516;
	int GEM_FOR_ACC = 36556;
	int DRESS_SHOES_BOX = 7113;

	public _033_MakeAPairOfDressShoes()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(30838);
		addTalkId(30838);
		addTalkId(30838);
		addTalkId(30164);
		addTalkId(31520);
		
		addLevelCheck("30838-00.htm", 60);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equals("30838-1.htm"))
		{
			st.setCond(1);
		}
		else if(event.equals("31520-1.htm"))
			st.setCond(2);
		else if(event.equals("30838-3.htm"))
			st.setCond(3);
		else if(event.equals("30838-5.htm"))
		{
			if(st.getQuestItemsCount(LEATHER) >= 360 && st.getQuestItemsCount(GEM_FOR_ACC) >= 90 && st.getQuestItemsCount(ADENA_ID) >= 500000)
			{
				st.takeItems(LEATHER, 360);
				st.takeItems(GEM_FOR_ACC, 90);
				st.takeItems(ADENA_ID, 500000);
				st.setCond(4);
			}
			else
				htmltext = "You don't have enough materials";
		}
		else if(event.equals("30164-1.htm"))
		{
			if(st.getQuestItemsCount(ADENA_ID) >= 300000)
			{
				st.takeItems(ADENA_ID, 300000);
				st.setCond(5);
			}
			else
				htmltext = "30164-havent.htm";
		}
		else if(event.equals("30838-7.htm"))
		{
			st.giveItems(DRESS_SHOES_BOX, 1);
			st.finishQuest();
		}
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		String htmltext = NO_QUEST_DIALOG;
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		if(npcId == 30838)
		{
			QuestState fwear = st.getPlayer().getQuestState(37);
			if(cond == 0 && st.getQuestItemsCount(DRESS_SHOES_BOX) == 0 && fwear != null && fwear.isStarted())
				htmltext = "30838-0.htm";
			else if(cond == 1)
				htmltext = "30838-1.htm";
			else if(cond == 2)
				htmltext = "30838-2.htm";
			else if(cond == 3 && st.getQuestItemsCount(LEATHER) >= 360 && st.getQuestItemsCount(GEM_FOR_ACC) >= 90 && st.getQuestItemsCount(ADENA_ID) >= 500000)
				htmltext = "30838-4.htm";
			else if(cond == 3 && (st.getQuestItemsCount(LEATHER) < 360 || st.getQuestItemsCount(GEM_FOR_ACC) < 90 || st.getQuestItemsCount(ADENA_ID) < 500000))
				htmltext = "30838-4r.htm";
			else if(cond == 4)
				htmltext = "30838-5r.htm";
			else if(cond == 5)
				htmltext = "30838-6.htm";
		}
		else if(npcId == 31520)
		{
			if(cond == 1)
				htmltext = "31520-0.htm";
			else if(cond == 2)
				htmltext = "31520-1r.htm";
		}
		else if(npcId == 30164)
			if(cond == 4)
				htmltext = "30164-0.htm";
			else if(cond == 5)
				htmltext = "30164-2.htm";
		return htmltext;
	}
}