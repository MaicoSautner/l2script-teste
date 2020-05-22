package services;

import java.util.ArrayList;
import java.util.List;

import l2s.gameserver.Config;
import l2s.gameserver.handler.bypass.Bypass;
import l2s.gameserver.instancemanager.ReflectionManager;
import l2s.gameserver.listener.script.OnInitScriptListener;
import l2s.gameserver.listener.zone.OnZoneEnterLeaveListener;
import l2s.gameserver.model.Creature;
import l2s.gameserver.model.Player;
import l2s.gameserver.model.Zone;
import l2s.gameserver.model.instances.NpcInstance;
import l2s.gameserver.network.l2.components.SystemMsg;
import l2s.gameserver.utils.Functions;
import l2s.gameserver.utils.Location;
import l2s.gameserver.utils.NpcUtils;
import l2s.gameserver.utils.PositionUtils;
import l2s.gameserver.utils.ReflectionUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeleToParnassus implements OnInitScriptListener
{
	private static final Logger _log = LoggerFactory.getLogger(TeleToGH.class);

	public class ZoneListener implements OnZoneEnterLeaveListener
	{
		@Override
		public void onZoneEnter(Zone zone, Creature cha)
		{
			// обрабатывать вход в зону не надо, только выход
		}

		@Override
		public void onZoneLeave(Zone zone, Creature cha)
		{
			Player player = cha.getPlayer();
			if(player != null)
			{
				if(Config.SERVICES_PARNASSUS_ENABLED && player.getReflection() == ReflectionManager.PARNASSUS && player.isVisible())
				{
					double angle = PositionUtils.convertHeadingToDegree(cha.getHeading()); // угол в градусах
					double radian = Math.toRadians(angle - 90); // угол в радианах
					cha.teleToLocation((int) (cha.getX() + 50 * Math.sin(radian)), (int) (cha.getY() - 50 * Math.cos(radian)), cha.getZ());
				}
			}
		}
	}

	private static Zone _zone = ReflectionUtils.getZone("[parnassus_offshore]");
	private static ZoneListener _zoneListener;

	@Override
	public void onInit()
	{
		if(!Config.SERVICES_PARNASSUS_ENABLED)
			return;

		ReflectionManager.PARNASSUS.setCoreLoc(new Location(149384, 171896, -952));

		// spawn wh keeper
		NpcUtils.spawnSingle(30086, new Location(149960, 174136, -920, 32768), ReflectionManager.PARNASSUS);
		// spawn grocery trader (Helvetia)
		NpcUtils.spawnSingle(30839, new Location(149368, 174264, -896, 49152), ReflectionManager.PARNASSUS);
		// spawn gk
		NpcUtils.spawnSingle(13129, new Location(149368, 172568, -952, 49152), ReflectionManager.PARNASSUS);
		// spawn Orion the Cat
		NpcUtils.spawnSingle(31860, new Location(148904, 173656, -952, 49152), ReflectionManager.PARNASSUS);
		// spawn blacksmith (Pushkin)
		NpcUtils.spawnSingle(30300, new Location(148760, 174136, -952, 0), ReflectionManager.PARNASSUS);
		// spawn Item Broker
		NpcUtils.spawnSingle(32320, new Location(149368, 173064, -952, 16384), ReflectionManager.PARNASSUS);

		_zoneListener = new ZoneListener();
		_zone.addListener(_zoneListener);
		_zone.setReflection(ReflectionManager.PARNASSUS);
		_zone.setActive(true);
		Zone zone = ReflectionUtils.getZone("[parnassus_peace]");
		zone.setReflection(ReflectionManager.PARNASSUS);
		zone.setActive(true);
		zone = ReflectionUtils.getZone("[parnassus_no_trade]");
		zone.setReflection(ReflectionManager.PARNASSUS);
		zone.setActive(true);

		_log.info("Loaded Service: Teleport to Parnassus");
	}

	@Bypass("services.TeleToParnassus:toParnassus")
	public void toParnassus(Player player, NpcInstance npc, String... arg)
	{
		if(player == null || npc == null)
			return;

		if(!npc.canBypassCheck(player))
			return;

		if(player.getAdena() < Config.SERVICES_PARNASSUS_PRICE)
		{
			player.sendPacket(SystemMsg.YOU_DO_NOT_HAVE_ENOUGH_ADENA);
			return;
		}

		player.reduceAdena(Config.SERVICES_PARNASSUS_PRICE, true);
		player.setVar("backCoords", player.getLoc().toXYZString(), -1);
		player.teleToLocation(Location.findPointToStay(_zone.getSpawn(), 30, 200, ReflectionManager.PARNASSUS.getGeoIndex()), ReflectionManager.PARNASSUS);
	}

	@Bypass("services.TeleToParnassus:fromParnassus")
	public void fromParnassus(Player player, NpcInstance npc, String... arg)
	{
		if(player == null || npc == null)
			return;

		if(!npc.canBypassCheck(player))
			return;

		String var = player.getVar("backCoords");
		if(var == null || var.equals(""))
		{
			teleOut(player, npc, arg);
			return;
		}
		player.teleToLocation(Location.parseLoc(var), ReflectionManager.MAIN);
	}

	@Bypass("services.TeleToParnassus:teleOut")
	public void teleOut(Player player, NpcInstance npc, String... arg)
	{
		player.teleToLocation(46776, 185784, -3528, ReflectionManager.MAIN);
		Functions.show(player.isLangRus() ? "Я не знаю, как Вы попали сюда, но я могу Вас отправить за ограждение." : "I don't know from where you came here, but I can teleport you the another border side.", player, npc);
	}
}