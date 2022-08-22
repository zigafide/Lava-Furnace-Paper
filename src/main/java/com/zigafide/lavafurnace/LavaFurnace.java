package com.zigafide.lavafurnace;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class LavaFurnace extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new FurnaceListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static class FurnaceListener implements Listener {
        @EventHandler
        public void place (PlayerBucketEmptyEvent e) {
            if(e.getBucket().equals(Material.LAVA_BUCKET)){
                Block b2 = e.getBlock().getLocation().clone().add(0,1,0).getBlock();
                if(b2.getType().equals(Material.FURNACE)){
                    Furnace furnace = (Furnace) b2.getState();
                    furnace.setBurnTime((short) 200);
                    furnace.update();
                }
            }
        }

        @EventHandler
        public void place (BlockFromToEvent e) {
            Block b = e.getBlock();
            Block b2 = e.getToBlock().getLocation().clone().add(0,1,0).getBlock();
            if(b2.getType().equals(Material.FURNACE) && b.getType().equals(Material.LAVA)){
                Furnace furnace = (Furnace) b2.getState();
                furnace.setBurnTime((short) 200);
                furnace.update();
            }
        }

        @EventHandler
        public void place (BlockPhysicsEvent e) {
            if(e.getBlock().getType().equals(Material.FURNACE) || e.getBlock().getType().equals(Material.BLAST_FURNACE)){
                Block b2 = e.getBlock().getLocation().clone().add(0,-1,0).getBlock();
                if(b2.getType().equals(Material.LAVA)){
                    Furnace furnace = (Furnace) e.getBlock().getState();
                    furnace.setBurnTime((short) 200);
                    furnace.update();
                }
            }
        }

    }


}
