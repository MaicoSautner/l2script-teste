package quests;

import l2s.commons.util.Rnd;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.model.quest.Quest;
import l2s.gameserver.model.quest.QuestState;

public class _420_LittleWings extends Quest
{
	// NPCs
	private static final int Cooper = 30829;
	private static final int Cronos = 30610;
	private static final int Byron = 30711;
	private static final int Maria = 30608;
	private static final int Mimyu = 30747;
	private static final int Exarion = 30748;
	private static final int Zwov = 30749;
	private static final int Kalibran = 30750;
	private static final int Suzet = 30751;
	private static final int Shamhai = 30752;
	// Mobs
	//TODO: private static final int Enchanted_Valey_First = 20589;
	//TODO: private static final int Enchanted_Valey_Last = 20599;
	private static final int Toad_Lord = 20231;
	private static final int Marsh_Spider = 20233;
	private static final int Leto_Lizardman_Warrior = 20580;
	private static final int Road_Scavenger = 20551;
	private static final int Breka_Orc_Overlord = 20270;
	private static final int Basilisk = 20072;
	private static final int Lesser_Basilisk = 20070;
	// Items
	private static final int Accessory_Gem = 36556; // Самоцвет для Аксессуара
	private static final int Armor_Fragment = 36551; // Обломок Доспеха
	private static final int COKES = 36561;	// Кокс
	private static final int GemstoneD = 2130;
	private static final int GemstoneC = 2131;
	private static final int Dragonflute_of_Wind = 3500;
	private static final int Dragonflute_of_Twilight = 3502;
	private static final int Hatchlings_Soft_Leather = 3912;
	private static final int Hatchlings_Mithril_Coat = 3918;
	private static final int Food_For_Hatchling = 4038;
	// Quest Items
	private static final int Fairy_Dust = 3499;
	private static final int Fairy_Stone = 3816;
	private static final int Deluxe_Fairy_Stone = 3817;
	private static final int Fairy_Stone_List = 3818;
	private static final int Deluxe_Fairy_Stone_List = 3819;
	private static final int Toad_Lord_Back_Skin = 3820;
	private static final int Juice_of_Monkshood = 3821;

	private static final int Scale_of_Drake_Exarion = 3822;
	private static final int Scale_of_Drake_Zwov = 3824;
	private static final int Scale_of_Drake_Kalibran = 3826;
	private static final int Scale_of_Wyvern_Suzet = 3828;
	private static final int Scale_of_Wyvern_Shamhai = 3830;

	private static final int Egg_of_Drake_Exarion = 3823;
	private static final int Egg_of_Drake_Zwov = 3825;
	private static final int Egg_of_Drake_Kalibran = 3827;
	private static final int Egg_of_Wyvern_Suzet = 3829;
	private static final int Egg_of_Wyvern_Shamhai = 3831;

	// Chances
	private static final int Toad_Lord_Back_Skin_Chance = 30;
	private static final int Egg_Chance = 50;
	private static final int Pet_Armor_Chance = 35;

	private static int[][] Fairy_Stone_Items = {
		{ Toad_Lord_Back_Skin, 10 },
		{ Accessory_Gem, 4 },
		{ Armor_Fragment, 2 },
		{ GemstoneD, 1 },
		{ COKES, 1 }
	};

	private static int[][] Delux_Fairy_Stone_Items = {
		{ Toad_Lord_Back_Skin, 20 },
		{ Accessory_Gem, 5 },
		{ Armor_Fragment, 2 },
		{ GemstoneC, 1 },
		{ COKES, 1 }
	};

	private static final int[][] wyrms = {
			{
					Leto_Lizardman_Warrior,
					Exarion,
					Scale_of_Drake_Exarion,
					Egg_of_Drake_Exarion
			},
			{
					Marsh_Spider,
					Zwov,
					Scale_of_Drake_Zwov,
					Egg_of_Drake_Zwov
			},
			{
					Road_Scavenger,
					Kalibran,
					Scale_of_Drake_Kalibran,
					Egg_of_Drake_Kalibran
			},
			{
					Breka_Orc_Overlord,
					Suzet,
					Scale_of_Wyvern_Suzet,
					Egg_of_Wyvern_Suzet
			},
			{
					Basilisk,
					Shamhai,
					Scale_of_Wyvern_Shamhai,
					Egg_of_Wyvern_Shamhai
			},
			{
					Lesser_Basilisk,
					Shamhai,
					Scale_of_Wyvern_Shamhai,
					Egg_of_Wyvern_Shamhai
			}
	};

	public _420_LittleWings()
	{
		super(PARTY_NONE, REPEATABLE);

		addStartNpc(Cooper);

		addTalkId(Cronos);
		addTalkId(Mimyu);
		addTalkId(Byron);
		addTalkId(Maria);

		addKillId(Toad_Lord);
		/*TODO:for(int Enchanted_Valey_id = Enchanted_Valey_First; Enchanted_Valey_id <= Enchanted_Valey_Last; Enchanted_Valey_id++)
			addKillId(Enchanted_Valey_id);*/

		for(int[] wyrm : wyrms)
		{
			addTalkId(wyrm[1]);
			addKillId(wyrm[0]);
		}

		addQuestItem(Fairy_Dust);
		addQuestItem(Fairy_Stone);
		addQuestItem(Deluxe_Fairy_Stone);
		addQuestItem(Fairy_Stone_List);
		addQuestItem(Deluxe_Fairy_Stone_List);
		addQuestItem(Toad_Lord_Back_Skin);
		addQuestItem(Juice_of_Monkshood);
		addQuestItem(Scale_of_Drake_Exarion);
		addQuestItem(Scale_of_Drake_Zwov);
		addQuestItem(Scale_of_Drake_Kalibran);
		addQuestItem(Scale_of_Wyvern_Suzet);
		addQuestItem(Scale_of_Wyvern_Shamhai);
		addQuestItem(Egg_of_Drake_Exarion);
		addQuestItem(Egg_of_Drake_Zwov);
		addQuestItem(Egg_of_Drake_Kalibran);
		addQuestItem(Egg_of_Wyvern_Suzet);
		addQuestItem(Egg_of_Wyvern_Shamhai);
		addLevelCheck("30829-00.htm", 35);	
	}

	@Override
	public String onEvent(String event, QuestState st, NpcInstance npc)
	{
		int cond = st.getCond();
		if(event.equalsIgnoreCase("30829-02.htm"))
		{
			st.setCond(1);
		}
		else if((event.equalsIgnoreCase("30610-05.htm") || event.equalsIgnoreCase("30610-12.htm")) && cond == 1)
		{
			st.setCond(2);
			st.takeItems(Fairy_Stone, -1);
			st.takeItems(Deluxe_Fairy_Stone, -1);
			st.takeItems(Fairy_Stone_List, -1);
			st.takeItems(Deluxe_Fairy_Stone_List, -1);
			st.giveItems(Fairy_Stone_List, 1);
		}
		else if((event.equalsIgnoreCase("30610-06.htm") || event.equalsIgnoreCase("30610-13.htm")) && cond == 1)
		{
			st.setCond(2);
			st.takeItems(Fairy_Stone, -1);
			st.takeItems(Deluxe_Fairy_Stone, -1);
			st.takeItems(Fairy_Stone_List, -1);
			st.takeItems(Deluxe_Fairy_Stone_List, -1);
			st.giveItems(Deluxe_Fairy_Stone_List, 1);
		}
		else if(event.equalsIgnoreCase("30608-03.htm") && cond == 2 && st.getQuestItemsCount(Fairy_Stone_List) > 0)
		{
			if(CheckFairyStoneItems(st, Fairy_Stone_Items))
			{
				st.setCond(3);
				TakeFairyStoneItems(st, Fairy_Stone_Items);
				st.giveItems(Fairy_Stone, 1);
			}
			else
				return "30608-01.htm";
		}
		else if(event.equalsIgnoreCase("30608-03a.htm") && cond == 2 && st.getQuestItemsCount(Deluxe_Fairy_Stone_List) > 0)
		{
			if(CheckFairyStoneItems(st, Delux_Fairy_Stone_Items))
			{
				st.setCond(3);
				TakeFairyStoneItems(st, Delux_Fairy_Stone_Items);
				st.giveItems(Deluxe_Fairy_Stone, 1);
			}
			else
				return "30608-01a.htm";
		}
		else if(event.equalsIgnoreCase("30711-03.htm") && cond == 3 && st.getQuestItemsCount(Fairy_Stone) + st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
		{
			st.setCond(4);
			if(st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
				return st.getInt("broken") == 1 ? "30711-04a.htm" : "30711-03a.htm";
			if(st.getInt("broken") == 1)
				return "30711-04.htm";
		}
		else if(event.equalsIgnoreCase("30747-02.htm") && cond == 4 && st.getQuestItemsCount(Fairy_Stone) > 0)
		{
			st.takeItems(Fairy_Stone, -1);
			st.set("takedStone", "1");
		}
		else if(event.equalsIgnoreCase("30747-02a.htm") && cond == 4 && st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
		{
			st.takeItems(Deluxe_Fairy_Stone, -1);
			st.set("takedStone", "2");
			st.giveItems(Fairy_Dust, 1);
			st.playSound(SOUND_ITEMGET);
		}
		else if(event.equalsIgnoreCase("30747-04.htm") && cond == 4 && st.getInt("takedStone") > 0)
		{
			st.setCond(5);
			st.unset("takedStone");
			st.giveItems(Juice_of_Monkshood, 1);
		}

		else if(event.equalsIgnoreCase("30748-02.htm") && cond == 5 && st.getQuestItemsCount(Juice_of_Monkshood) > 0)
		{
			st.setCond(6);
			st.takeItems(Juice_of_Monkshood, -1);
			st.giveItems(3822, 1);
		}

		else if(event.equalsIgnoreCase("30749-02.htm") && cond == 5 && st.getQuestItemsCount(Juice_of_Monkshood) > 0)
		{
			st.setCond(6);
			st.takeItems(Juice_of_Monkshood, -1);
			st.giveItems(3824, 1);
		}

		else if(event.equalsIgnoreCase("30750-02.htm") && cond == 5 && st.getQuestItemsCount(Juice_of_Monkshood) > 0)
		{
			st.setCond(6);
			st.takeItems(Juice_of_Monkshood, -1);
			st.giveItems(3826, 1);
		}

		else if(event.equalsIgnoreCase("30751-02.htm") && cond == 5 && st.getQuestItemsCount(Juice_of_Monkshood) > 0)
		{
			st.setCond(6);
			st.takeItems(Juice_of_Monkshood, -1);
			st.giveItems(3828, 1);
		}

		else if(event.equalsIgnoreCase("30752-02.htm") && cond == 5 && st.getQuestItemsCount(Juice_of_Monkshood) > 0)
		{
			st.setCond(6);
			st.takeItems(Juice_of_Monkshood, -1);
			st.giveItems(3830, 1);
		}

		else if(event.equalsIgnoreCase("30747-09.htm") && cond == 7)
		{
			int egg_id = 0;
			for(int[] wyrm : wyrms)
				if(st.getQuestItemsCount(wyrm[2]) == 0 && st.getQuestItemsCount(wyrm[3]) >= 1)
				{
					egg_id = wyrm[3];
					break;
				}
			if(egg_id == 0)
				return NO_QUEST_DIALOG;
			st.takeItems(egg_id, -1);
			st.giveItems(Rnd.get(Dragonflute_of_Wind, Dragonflute_of_Twilight), 1);
			if(st.getQuestItemsCount(Fairy_Dust) > 0)
			{
				st.playSound(SOUND_MIDDLE);
				return "30747-09a.htm";
			}
			st.finishQuest();
		}
		else if(event.equalsIgnoreCase("30747-10.htm") && cond == 7)
		{
			st.finishQuest();
		}
		else if(event.equalsIgnoreCase("30747-11.htm") && cond == 7)
		{
			st.finishQuest();
			if(st.getQuestItemsCount(Fairy_Dust) == 0)
				return "30747-10.htm";
			st.takeItems(Fairy_Dust, -1);
			if(Rnd.chance(Pet_Armor_Chance))
			{
				int armor_id = Hatchlings_Soft_Leather + Rnd.get((int) st.getRateQuestsReward());
				if(armor_id > Hatchlings_Mithril_Coat)
					armor_id = Hatchlings_Mithril_Coat;
				st.giveItems(armor_id, 1);
			}
			else
				st.giveItems(Food_For_Hatchling, 20, true);
		}

		return event;
	}

	@Override
	public String onTalk(NpcInstance npc, QuestState st)
	{
		int npcId = npc.getNpcId();
		int cond = st.getCond();
		int broken = st.getInt("broken");

		if(npcId == Cooper)
		{
			if(cond == 0)
				return "30829-01.htm";
			if(cond == 1)
				return "30829-02.htm";
			return "30829-03.htm";
		}

		if(npcId == Cronos)
		{
			if(cond == 1)
				return broken == 1 ? "30610-10.htm" : "30610-01.htm";
			if(cond == 2)
				return "30610-07.htm";
			if(cond == 3)
				return broken == 1 ? "30610-14.htm" : "30610-08.htm";
			if(cond == 4)
				return "30610-09.htm";
			if(cond > 4)
				return "30610-11.htm";
		}

		if(npcId == Maria)
			if(cond == 2)
			{
				if(st.getQuestItemsCount(Deluxe_Fairy_Stone_List) > 0)
					return CheckFairyStoneItems(st, Delux_Fairy_Stone_Items) ? "30608-02a.htm" : "30608-01a.htm";
				if(st.getQuestItemsCount(Fairy_Stone_List) > 0)
					return CheckFairyStoneItems(st, Fairy_Stone_Items) ? "30608-02.htm" : "30608-01.htm";
			}
			else if(cond > 2)
				return "30608-04.htm";

		if(npcId == Byron)
		{
			if(cond == 1 && broken == 1)
				return "30711-06.htm";
			if(cond == 2 && broken == 1)
				return "30711-07.htm";
			if(cond == 3 && st.getQuestItemsCount(Fairy_Stone) + st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
				return "30711-01.htm";
			if(cond >= 4 && st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
				return "30711-05a.htm";
			if(cond >= 4 && st.getQuestItemsCount(Fairy_Stone) > 0)
				return "30711-05.htm";
		}

		if(npcId == Mimyu)
		{
			if(cond == 4 && st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
				return "30747-01a.htm";
			if(cond == 4 && st.getQuestItemsCount(Fairy_Stone) > 0)
				return "30747-01.htm";
			if(cond == 5)
				return "30747-05.htm";
			if(cond == 6)
			{
				for(int[] wyrm : wyrms)
					if(st.getQuestItemsCount(wyrm[2]) == 0 && st.getQuestItemsCount(wyrm[3]) >= 20)
						return "30747-07.htm";
				return "30747-06.htm";
			}
			if(cond == 7)
				for(int[] wyrm : wyrms)
					if(st.getQuestItemsCount(wyrm[2]) == 0 && st.getQuestItemsCount(wyrm[3]) >= 1)
						return "30747-08.htm";
		}

		if(npcId >= Exarion && npcId <= Shamhai)
		{
			if(cond == 5 && st.getQuestItemsCount(Juice_of_Monkshood) > 0)
				return String.valueOf(npcId) + "-01.htm";
			if(cond == 6 && st.getQuestItemsCount(getWyrmScale(npcId)) > 0)
			{
				int egg_id = getWyrmEgg(npcId);
				if(st.getQuestItemsCount(egg_id) < 20)
					return String.valueOf(npcId) + "-03.htm";
				st.takeItems(getWyrmScale(npcId), -1);
				st.takeItems(egg_id, -1);
				st.giveItems(egg_id, 1);
				st.setCond(7);
				return String.valueOf(npcId) + "-04.htm";
			}
			if(cond == 7 && st.getQuestItemsCount(getWyrmEgg(npcId)) == 1)
				return String.valueOf(npcId) + "-05.htm";
		}

		return NO_QUEST_DIALOG;
	}

	@Override
	public String onKill(NpcInstance npc, QuestState st)
	{
		if(!st.isStarted())
			return null;
		int npcId = npc.getNpcId();
		int cond = st.getCond();

		if(cond == 2 && npcId == Toad_Lord)
		{
			int needed_skins = getNeededSkins(st);
			if(st.getQuestItemsCount(Toad_Lord_Back_Skin) < needed_skins && Rnd.chance(Toad_Lord_Back_Skin_Chance))
			{
				st.giveItems(Toad_Lord_Back_Skin, 1);
				st.playSound(st.getQuestItemsCount(Toad_Lord_Back_Skin) < needed_skins ? SOUND_ITEMGET : SOUND_MIDDLE);
			}
			return null;
		}

		/*TODO: if(npcId >= Enchanted_Valey_First && npcId <= Enchanted_Valey_Last && st.getQuestItemsCount(Deluxe_Fairy_Stone) > 0)
		{
			st.takeItems(Deluxe_Fairy_Stone, 1);
			st.set("broken", "1");
			st.setCond(1);
			return "You lost fairy stone deluxe!";
		}*/

		if(cond == 6)
		{
			int wyrm_id = isWyrmStoler(npcId);
			if(wyrm_id > 0 && st.getQuestItemsCount(getWyrmScale(wyrm_id)) > 0 && st.getQuestItemsCount(getWyrmEgg(wyrm_id)) < 20 && Rnd.chance(Egg_Chance))
			{
				st.giveItems(getWyrmEgg(wyrm_id), 1);
				st.playSound(st.getQuestItemsCount(getWyrmEgg(wyrm_id)) < 20 ? SOUND_ITEMGET : SOUND_MIDDLE);
			}
		}

		return null;
	}

	private static int getWyrmScale(int npc_id)
	{
		for(int[] wyrm : wyrms)
			if(npc_id == wyrm[1])
				return wyrm[2];
		return 0;
	}

	private static int getWyrmEgg(int npc_id)
	{
		for(int[] wyrm : wyrms)
			if(npc_id == wyrm[1])
				return wyrm[3];
		return 0;
	}

	private static int isWyrmStoler(int npc_id)
	{
		for(int[] wyrm : wyrms)
			if(npc_id == wyrm[0])
				return wyrm[1];
		return 0;
	}

	public static int getNeededSkins(QuestState st)
	{
		if(st.getQuestItemsCount(Deluxe_Fairy_Stone_List) > 0)
			return 20;
		if(st.getQuestItemsCount(Fairy_Stone_List) > 0)
			return 10;
		return -1;
	}

	public static boolean CheckFairyStoneItems(QuestState st, int[][] item_list)
	{
		for(int[] _item : item_list)
			if(st.getQuestItemsCount(_item[0]) < _item[1])
				return false;
		return true;
	}

	public static void TakeFairyStoneItems(QuestState st, int[][] item_list)
	{
		for(int[] _item : item_list)
			st.takeItems(_item[0], _item[1]);
	}
}