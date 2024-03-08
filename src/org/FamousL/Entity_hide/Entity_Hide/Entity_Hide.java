/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.FamousL.Entity_hide.Entity_Hide;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;




public class Entity_Hide extends JavaPlugin{
	public final static String pluginDirPath = "plugins" + File.separator + "Entity_Hide" + File.separator;
	public static Entity_Hide instance;
	public Config config; 

    @Override  
    public void onEnable() {
        getLogger().info("The Entity_Hide plugin has been loaded");
        new Hide_Stuff(this);
        Entity_Hide.instance=this;
        
        this.config = new Config();
        this.config.loadConfig();// loads config or default
        this.config.saveConfig();// save eventual default
        
    }

    @Override
    public void onDisable() {
        getLogger().info("The Entity_Hide plugin has been unloaded");
    }
    

}
