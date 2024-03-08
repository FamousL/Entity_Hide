package org.FamousL.Entity_hide.Entity_Hide;

import java.util.Arrays;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import me.EtienneDx.AnnotationConfig.AnnotationConfig;
import me.EtienneDx.AnnotationConfig.ConfigField;
import me.EtienneDx.AnnotationConfig.ConfigFile;




@ConfigFile(header = "RealEstate wiki and newest versions are available at http://www.github.com/FamousL/RealEstate")
public class Config extends AnnotationConfig
{
	
	@ConfigField(name="Entity_Hide.signdistance",comment="The distance at which signs will cease rendering for players")
	public int signrenderdistance =5;
	@ConfigField(name="Entity_Hide.invdistance",comment="The distance at which Inventory Blocks will cease rendering for players")
	public int invrenderdistance =20;
	
    public PluginDescriptionFile pdf;

    public final String configFilePath = Entity_Hide.pluginDirPath + "config.yml";

    public Config()
    {
        this.pdf = Entity_Hide.instance.getDescription();
        
    }
    
    public String getString(List<String> li)
    {
    	return String.join(";", li);
    }
    
    public List<String> getList(String str)
    {
    	return Arrays.asList(str.split(";"));
    }
    
    List<String> getConfigList(YamlConfiguration config, String path, List<String> defVal)
    {
    	config.addDefault(path, defVal);
    	List<String> ret = config.getStringList(path);
    	ret.replaceAll(String::toLowerCase);
    	return ret;
    }
    
    @Override
    public void loadConfig()
    {
        //YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(this.configFilePath));
        this.loadConfig(this.configFilePath);
    }
}
