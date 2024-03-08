package org.FamousL.Entity_hide.Entity_Hide;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;

public class Hide_Stuff {
    
    private  Entity_Hide pluginManager;

    public Hide_Stuff( Plugin pluginManager) {

        this.pluginManager = (Entity_Hide) pluginManager;
        this.hideSigns();
    }

    public void hideSigns() {
    	new BukkitRunnable() {
    		@Override
    		public void run() {
    			for (Player player : Bukkit.getOnlinePlayers()) {
    				
    				ArrayList<Chunk> playerChunks=getChunks((Entity) player);
    				for (Chunk curchunk: playerChunks) {
    					
    					for (BlockState block: curchunk.getTileEntities()) {
    						
    						if (block instanceof Sign) {
    							
    							if(!inrange(player,block,Entity_Hide.instance.config.signrenderdistance)) {
    								BlockData air= Bukkit.createBlockData(Material.AIR);
    								player.sendBlockChange(block.getLocation(), air);
    							
    								continue;
    							}
    							else
    							{
    								player.sendBlockChange(block.getLocation(),block.getBlockData());  							
    								Sign thissign=(Sign) block;
    								thissign.update();
    								
    								continue;
    							}
    							
    						}
    						if(block instanceof BlockInventoryHolder)
    						{
    							if(inrange(player,block,Entity_Hide.instance.config.invrenderdistance)) 
    							{
    								player.sendBlockChange(block.getLocation(),block.getBlockData());
    								block.setBlockData(block.getBlockData());
    								continue;
    							}
    							else
    							{
    								BlockData air= Bukkit.createBlockData(Material.AIR);
    								player.sendBlockChange(block.getLocation(), air);
    							
    								continue;	
    								
    							}
    							
    						}
    					}
    				}
    			}
    		}
    	}.runTaskTimer((Plugin) pluginManager,(long) 0, (long)20); // Run every 20 ticks (1 second)
    }
    public boolean inrange(Player player, BlockState block,int distance) 
    {
    	Location playerloc=player.getLocation();
    	Location sign=block.getLocation();
    	if (playerloc.getX()<sign.getX()+distance&&playerloc.getX()>sign.getX()-distance) 
    	{
    		if (playerloc.getY()<sign.getY()+distance&&playerloc.getY()>sign.getY()-distance) 
    		{    		
    			if (playerloc.getZ()<sign.getZ()+distance&&playerloc.getZ()>sign.getZ()-distance) 
    			{
    				return true;    			
    			}
    			
    		}
    	}
    	return false;

    }
    public ArrayList<Chunk> getChunks(Entity entity) {
        ArrayList<Chunk> chunks = new ArrayList<Chunk>();

        
        int renderDistance = entity.getServer().getViewDistance()*16;//viewdistance is number of chunks :S
        
        Location playerat=entity.getLocation();
        
        
        for (double x = playerat.getX() - renderDistance; x <= playerat.getX() + renderDistance+16; x+=16) {
            for (double z = playerat.getZ() - renderDistance; z <= playerat.getZ() + renderDistance+16; z+=16) {
                if (!chunks.contains(new Location(entity.getWorld(), x, 1, z).getChunk())) {
                	
                    chunks.add(new Location(entity.getWorld(), x, 1, z).getChunk());
                    
                
                }
            }
        }

        return chunks;
    }

}
