package quests;

import org.apache.commons.lang3.ArrayUtils;
import l2s.gameserver.instancemanager.DailyQuestsManager;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

//By Evil_dnk dev.fairytale-world.ru
public class _488_WondersofCaring extends Quest
{
	//Шанс дропа
	private static final int chance = 39;
	//Квест итем
	private static final int Box = 19500;
	//Монстры
	private static final int[] mobstohunt = {20965, 20970, 20971, 20972, 20966, 20967, 20973, 20968, 20969};
	//НПСы
	private static final int Dolphr = 32880;
	private static final int Adventurequid = 33463;

	private static final int EXP_REWARD = 22901550;
	{
		super(PARTY_NONE, DAILY);
		addStartNpc(Adventurequid);
		addTalkId(Adventurequid);
		addTalkId(Dolphr);
		addKillId(mobstohunt);
		addQuestItem(Box);
		addLevelCheck("0-nc.htm", 75/*, 79*/);
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		String htmltext = event;
		if(event.equalsIgnoreCase("quest_ac"))
		{
			st.setCond(1);
			htmltext = "0-4.htm";
		}
		else if(event.equalsIgnoreCase("qet_rev"))
			htmltext = "1-3.htm";
		return htmltext;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int cond = st.getCond();
		int npcId = npc.getNpcId();
		String htmltext = NO_QUEST_DIALOG;
		if(npcId == Adventurequid)
		{
			if(cond == 0)
				htmltext = "0-1.htm";
			else if(cond == 1 || cond == 2)
				htmltext = "0-5.htm";
		}
		else if(npcId == Dolphr)
		{
			if(cond == 0)
			{
				if(checkStartCondition(npc, st.getPlayer()) != null)
					htmltext = "1-nc.htm";
			}
			else if(cond == 2)
			{
				htmltext = "1-1.htm";
				st.addExpAndSp(EXP_REWARD, SP_REWARD);
				st.giveItems(57, 283800);
				st.takeAllItems(Box);
				st.finishQuest();
			}
		}
		return htmltext;
	}

	@Override
	public String onCompleted(NpcInstance npc, QuestState st)
	{
		String htmltext = COMPLETED_DIALOG;
		if(npc.getNpcId() == Adventurequid)
			htmltext = "0-c.htm";
		else if(npc.getNpcId() == Dolphr)
			htmltext = "1-c.htm";
		return htmltext;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		if(st.getCond() == 1 && ArrayUtils.contains(mobstohunt, npcId))
		{
			if(st.rollAndGive(Box, 1, 1, 50, chance))
				st.setCond(2);
		}
		return null;
	}

	@Override
	public boolean isVisible(Player player)
	{
		if(DailyQuestsManager.isQuestDisabled(getId()))
			return false;
		return true;
	}
}