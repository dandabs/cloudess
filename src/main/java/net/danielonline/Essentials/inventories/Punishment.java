package net.danielonline.Essentials.inventories;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

public class Punishment implements InventoryProvider {

    public static String name = "";

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("punishmentInventory")
            .provider(new Punishment())
            .size(6, 9)
            .title(ChatColor.BLUE + "Punishment GUI for " + ChatColor.GOLD + name)
            .build();

    private final Random random = new Random();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillBorders(ClickableItem.empty(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)));

        ItemStack fire = new ItemStack(Material.FIRE); ItemMeta firem = fire.getItemMeta(); // crossteam griefing
        ItemStack brown = new ItemStack(Material.BROWN_TERRACOTTA); ItemMeta brownm = brown.getItemMeta(); // racism
        ItemStack torch = new ItemStack(Material.TORCH); ItemMeta torchm = torch.getItemMeta();// other discrimination
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD); ItemMeta skullm = skull.getItemMeta();// doxxing
        ItemStack chest = new ItemStack(Material.CHEST); ItemMeta chestm = chest.getItemMeta();// stealing objects
        ItemStack barrier = new ItemStack(Material.BARRIER); ItemMeta barrierm = barrier.getItemMeta();// nsfw builds
        ItemStack hopper = new ItemStack(Material.HOPPER); ItemMeta hopperm = hopper.getItemMeta();// nsfw chat
        ItemStack redstone = new ItemStack(Material.REDSTONE); ItemMeta redstonem = redstone.getItemMeta();// hacked clients or mods
        ItemStack repeater = new ItemStack(Material.REPEATER); ItemMeta repeaterm = repeater.getItemMeta();// causing lag
        ItemStack jack = new ItemStack(Material.JACK_O_LANTERN); ItemMeta jackm = jack.getItemMeta(); // causing drama
        ItemStack door = new ItemStack(Material.DARK_OAK_DOOR); ItemMeta doorm = door.getItemMeta();// ignoring staff
        ItemStack dirt = new ItemStack(Material.DIRT); ItemMeta dirtm = dirt.getItemMeta();// illegal activity
        ItemStack beacon = new ItemStack(Material.BEACON); ItemMeta beaconm = beacon.getItemMeta();// cheating (with alts)
        ItemStack creeper = new ItemStack(Material.CREEPER_HEAD); ItemMeta creeperm = creeper.getItemMeta();// offensive username / skin
        ItemStack gunpowder = new ItemStack(Material.GUNPOWDER); ItemMeta gunpowderm = gunpowder.getItemMeta();// scamming


        ItemStack mute = new ItemStack(Material.LEVER); ItemMeta mutem = mute.getItemMeta();// Misc. mute
        ItemStack ban = new ItemStack(Material.LEVER); ItemMeta banm = ban.getItemMeta();// Misc. ban
        ItemStack kick = new ItemStack(Material.LEVER); ItemMeta kickm = kick.getItemMeta();// Misc. warning

        firem.setDisplayName(ChatColor.RED + "Griefing"); fire.setItemMeta(firem);
        brownm.setDisplayName(ChatColor.RED + "Racism"); brown.setItemMeta(brownm);
        torchm.setDisplayName(ChatColor.RED + "Discrimination"); torch.setItemMeta(torchm);
        skullm.setDisplayName(ChatColor.RED + "Doxxing"); skull.setItemMeta(skullm);
        chestm.setDisplayName(ChatColor.RED + "Stealing"); chest.setItemMeta(chestm);
        barrierm.setDisplayName(ChatColor.RED + "NSFW Builds"); barrier.setItemMeta(barrierm);
        hopperm.setDisplayName(ChatColor.RED + "NSFW Chat"); hopper.setItemMeta(hopperm);
        redstonem.setDisplayName(ChatColor.RED + "Mods"); redstone.setItemMeta(redstonem);
        repeaterm.setDisplayName(ChatColor.RED + "Lag"); repeater.setItemMeta(repeaterm);
        jackm.setDisplayName(ChatColor.RED + "Drama"); jack.setItemMeta(jackm);
        doorm.setDisplayName(ChatColor.RED + "Disrespecting Staff"); door.setItemMeta(doorm);
        dirtm.setDisplayName(ChatColor.RED + "Illegal Activity"); dirt.setItemMeta(dirtm);
        beaconm.setDisplayName(ChatColor.RED + "Cheating"); beacon.setItemMeta(beaconm);
        creeperm.setDisplayName(ChatColor.RED + "Offensive Player"); creeper.setItemMeta(creeperm);
        gunpowderm.setDisplayName(ChatColor.RED + "Scamming"); gunpowder.setItemMeta(gunpowderm);

        mutem.setDisplayName(ChatColor.GOLD + "Misc. Mute"); mute.setItemMeta(mutem);
        banm.setDisplayName(ChatColor.GOLD + "Misc. Ban"); ban.setItemMeta(banm);
        kickm.setDisplayName(ChatColor.GOLD + "Misc. Kick"); kick.setItemMeta(kickm);


        contents.set(3, 2, ClickableItem.of(fire,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Griefing", "3d")));

        contents.set(4, 2, ClickableItem.of(brown,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Racism", "1y")));

        contents.set(5, 2, ClickableItem.of(torch,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Discrimination", "6mo")));

        contents.set(6, 2, ClickableItem.of(skull,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Doxxing", "3mo")));

        contents.set(7, 2, ClickableItem.of(chest,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Stealing", "1h")));


        contents.set(3, 3, ClickableItem.of(barrier,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "NSFW Builds", "1mo")));

        contents.set(4, 3, ClickableItem.of(hopper,
                e -> new net.danielonline.Essentials.utils.Punishment().tempmute(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "NSFW Chat", "6h")));

        contents.set(5, 3, ClickableItem.of(redstone,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Mods", "1w")));

        contents.set(6, 3, ClickableItem.of(repeater,
                e -> new net.danielonline.Essentials.utils.Punishment().warn(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Lag")));

        contents.set(7, 3, ClickableItem.of(jack,
                e -> new net.danielonline.Essentials.utils.Punishment().warn(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Drama")));


        contents.set(3, 4, ClickableItem.of(door,
                e -> new net.danielonline.Essentials.utils.Punishment().warn(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Disrespecting Staff")));

        contents.set(4, 4, ClickableItem.of(dirt,
                e -> new net.danielonline.Essentials.utils.Punishment().permban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Illegal Activity")));

        contents.set(5, 4, ClickableItem.of(beacon,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Cheating", "1d")));

        contents.set(6, 4, ClickableItem.of(creeper,
                e -> new net.danielonline.Essentials.utils.Punishment().permban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Offensive Skin / Username")));

        contents.set(7, 2, ClickableItem.of(gunpowder,
                e -> new net.danielonline.Essentials.utils.Punishment().tempban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Scamming", "3h")));


        contents.set(4, 4, ClickableItem.of(mute,
                e -> new net.danielonline.Essentials.utils.Punishment().permmute(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Misc. Mute")));

        contents.set(5, 4, ClickableItem.of(ban,
                e -> new net.danielonline.Essentials.utils.Punishment().permban(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Misc. Ban")));

        contents.set(6, 4, ClickableItem.of(kick,
                e -> new net.danielonline.Essentials.utils.Punishment().kick(player, ChatColor.stripColor(INVENTORY.getTitle().split(" ")[3]), "Misc. Kick")));


    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 5 != 0)
            return;

        short durability = (short) random.nextInt(15);

        ItemStack glass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1, durability);
        contents.fillBorders(ClickableItem.empty(glass));
    }

}
