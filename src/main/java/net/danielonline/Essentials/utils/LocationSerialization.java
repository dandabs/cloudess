package net.danielonline.Essentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

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

}
