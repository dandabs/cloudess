package net.danielonline.Essentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class LocationSerialization {

    public String serialize(Location loc) {
        World world = loc.getWorld();
        double x = loc.getX();
        double y = loc.getY();
        double z = loc.getZ();
        float yaw = loc.getYaw();
        float pitch = loc.getPitch();
        return world.getName() + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
    }

    public Location deserialize(String string) {
        String[] vals = string.split(";");
        World world = Bukkit.getWorld(vals[0]);
        double x = Double.parseDouble(vals[1]);
        double y = Double.parseDouble(vals[2]);
        double z = Double.parseDouble(vals[3]);
        float yaw = Float.parseFloat(vals[4]);
        float pitch = Float.parseFloat(vals[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public String getDirection(Player player) {
        double rot = (player.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        if (0 <= rot && rot < 67.5) {
            return "W";
        } else if (67.5 <= rot && rot < 157.5) {
            return "N";
        } else if (157.5 <= rot && rot < 247.5) {
            return "E";
        } else if (247.5 <= rot && rot < 360.0) {
            return "S";
        } else {
            return null;
        }
    }

}
