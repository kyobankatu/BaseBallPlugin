package main.java.main;

import main.java.Events.ThrowBall;
import main.java.PlayerInfo.*;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import main.java.pitches.SliderORSinking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.*;

import static main.java.PlayerInfo.EachPlayerInfo.getEachPlayerInfo;
import static main.java.PlayerInfo.PlayerInfoList.getPlayerInfoList;
import static main.java.main.StartCommand.pitcher;


public final class Main extends JavaPlugin implements Listener,CommandExecutor {

    public static Logger logger;
    public static Plugin plugin;
    public static FileConfiguration config;
    public static HashMap<Snowball,CatchTimer>catchmap=new HashMap<>();
    static HashMap<Player,RunSpeedTimer>runtimer=new HashMap<>();
    public static ItemMeta fastballm,sliderm,sinkingm,changem,cutm,verticalm,forkm,slowcurvem,curvem,powercurvem,sffm,oneseamm,slurvem,dcurvem,twoseamm,fastsliderm,fasthardsinkerm,movem,spikem,fastscrewm,sinkerm,fastsinkerm;
    public static ItemStack fastballi,slideri,sinkingi,changei,cuti,verticali,forki,slowcurvei,curvei,powercurvei,sffi,oneseami,slurvei,dcurvei,twoseami,fastslideri,fasthardsinkeri,movei,spikei,fastscrewi,sinkeri,fastsinkeri;
    public static ItemStack standbarrier = new ItemStack(Material.BARRIER);
    public static ItemStack foulbarrier = new ItemStack(Material.BARRIER);
    public static EachPlayerInfo EachPlayerInfo;
    public static PlayerInfoList PlayerInfoList;
    public static ArrayList<Player> hokyulist=new ArrayList<>();

    //TODO:スクリュー実装


    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        config = getConfig();
        plugin = this;
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new AreaCommand(), this);
        Bukkit.getPluginManager().registerEvents(new ThrowBall(), this);
        getCommand("start").setExecutor(new StartCommand());
        getCommand("throw").setExecutor(new ThrowCommand());
        getCommand("batting").setExecutor(new BattingCommand());
        getCommand("area").setExecutor(new AreaCommand());


        String fastball = "ストレート";
        fastballi = new ItemStack(Material.SNOWBALL);
        fastballm=fastballi.getItemMeta();
        fastballm.setDisplayName(fastball);
        fastballi.setItemMeta(fastballm);

        String slider = "スライダー";
        slideri = new ItemStack(Material.SNOWBALL);
        sliderm=slideri.getItemMeta();
        sliderm.setDisplayName(slider);
        slideri.setItemMeta(sliderm);

        String sinking = "シュート";
        sinkingi = new ItemStack(Material.SNOWBALL);
        sinkingm=sinkingi.getItemMeta();
        sinkingm.setDisplayName(sinking);
        sinkingi.setItemMeta(sinkingm);

        String change = "チェンジアップ";
        changei = new ItemStack(Material.SNOWBALL);
        changem=changei.getItemMeta();
        changem.setDisplayName(change);
        changei.setItemMeta(changem);

        String cut = "カット";
        cuti = new ItemStack(Material.SNOWBALL);
        cutm=cuti.getItemMeta();
        cutm.setDisplayName(cut);
        cuti.setItemMeta(cutm);

        String vertical = "縦スライダー";
        verticali = new ItemStack(Material.SNOWBALL);
        verticalm=verticali.getItemMeta();
        verticalm.setDisplayName(vertical);
        verticali.setItemMeta(verticalm);

        String fork = "フォーク";
        forki = new ItemStack(Material.SNOWBALL);
        forkm=forki.getItemMeta();
        forkm.setDisplayName(fork);
        forki.setItemMeta(forkm);

        String slowcurve = "スローカーブ";
        slowcurvei = new ItemStack(Material.SNOWBALL);
        slowcurvem=slowcurvei.getItemMeta();
        slowcurvem.setDisplayName(slowcurve);
        slowcurvei.setItemMeta(slowcurvem);

        String curve = "カーブ";
        curvei = new ItemStack(Material.SNOWBALL);
        curvem=curvei.getItemMeta();
        curvem.setDisplayName(curve);
        curvei.setItemMeta(curvem);

        String powercurve = "パワーカーブ";
        powercurvei = new ItemStack(Material.SNOWBALL);
        powercurvem=powercurvei.getItemMeta();
        powercurvem.setDisplayName(powercurve);
        powercurvei.setItemMeta(powercurvem);

        String sff = "SFF";
        sffi = new ItemStack(Material.SNOWBALL);
        sffm=sffi.getItemMeta();
        sffm.setDisplayName(sff);
        sffi.setItemMeta(sffm);

        String oneseam = "ワンシーム";
        oneseami = new ItemStack(Material.SNOWBALL);
        oneseamm=oneseami.getItemMeta();
        oneseamm.setDisplayName(oneseam);
        oneseami.setItemMeta(oneseamm);

        String slurve = "スラーブ";
        slurvei = new ItemStack(Material.SNOWBALL);
        slurvem=slurvei.getItemMeta();
        slurvem.setDisplayName(slurve);
        slurvei.setItemMeta(slurvem);

        String dcurve = "Dカーブ";
        dcurvei = new ItemStack(Material.SNOWBALL);
        dcurvem=dcurvei.getItemMeta();
        dcurvem.setDisplayName(dcurve);
        dcurvei.setItemMeta(dcurvem);

        String twoseam = "ツーシーム";
        twoseami = new ItemStack(Material.SNOWBALL);
        twoseamm=twoseami.getItemMeta();
        twoseamm.setDisplayName(twoseam);
        twoseami.setItemMeta(twoseamm);

        String fastslider = "高速スライダー";
        fastslideri = new ItemStack(Material.SNOWBALL);
        fastsliderm=fastslideri.getItemMeta();
        fastsliderm.setDisplayName(fastslider);
        fastslideri.setItemMeta(fastsliderm);

        String fasthardsinker = "高速シュート";
        fasthardsinkeri = new ItemStack(Material.SNOWBALL);
        fasthardsinkerm=fasthardsinkeri.getItemMeta();
        fasthardsinkerm.setDisplayName(fasthardsinker);
        fasthardsinkeri.setItemMeta(fasthardsinkerm);

        String move = "ムービングファスト";
        movei = new ItemStack(Material.SNOWBALL);
        movem=movei.getItemMeta();
        movem.setDisplayName(move);
        movei.setItemMeta(movem);

        String spike = "スパイクカーブ";
        spikei = new ItemStack(Material.SNOWBALL);
        spikem=spikei.getItemMeta();
        spikem.setDisplayName(spike);
        spikei.setItemMeta(spikem);

        String fastscrew = "高速スクリュー";
        fastscrewi = new ItemStack(Material.SNOWBALL);
        fastscrewm=fastscrewi.getItemMeta();
        fastscrewm.setDisplayName(fastscrew);
        fastscrewi.setItemMeta(fastscrewm);

        String sinker = "シンカー";
        sinkeri = new ItemStack(Material.SNOWBALL);
        sinkerm=sinkeri.getItemMeta();
        sinkerm.setDisplayName(sinker);
        sinkeri.setItemMeta(sinkerm);

        String fastsinker = "高速シンカー";
        fastsinkeri = new ItemStack(Material.SNOWBALL);
        fastsinkerm=fastsinkeri.getItemMeta();
        fastsinkerm.setDisplayName(fastsinker);
        fastsinkeri.setItemMeta(fastsinkerm);

        logger = Logger.getLogger(Main.class.getName());
        try {
            LogManager manager = LogManager.getLogManager();
            File newfile = new File("BaseBallPluginLog.properties");
            newfile.createNewFile();
            manager.readConfiguration(new FileInputStream("BaseBallPluginLog.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //join
    @EventHandler
    public void Join(PlayerJoinEvent e){
        EachPlayerInfo=getEachPlayerInfo();
        PlayerInfoList=getPlayerInfoList();
        pitcher=e.getPlayer();
        new SetInfoList();
        PitcherInfo info=PlayerInfoList.getRandomPitcherInfo();
        EachPlayerInfo.addInfo(pitcher,info);
        //new RunSpeedTimer(pitcher,null,info.getArea()*0.00006F);
        Bukkit.broadcastMessage(info.toString());
        logger.info(e.getPlayer().getUniqueId()+"："+info.toString());
    }

    //Quit
    @EventHandler
    public void Quit(PlayerQuitEvent e){
        Player p=e.getPlayer();
        if(runtimer.containsKey(p))
            runtimer.get(p).stop();
    }

    //破壊イベント
    @EventHandler
    public void Break(BlockBreakEvent e){
        if(e.getPlayer().getInventory().getItemInMainHand().getType()==Material.STICK)
            e.setCancelled(true);
    }

    //捕球
    @EventHandler
    public void Move(PlayerMoveEvent e){
        if(hokyulist.contains(e.getPlayer()))
            e.setCancelled(true);
    }

    //バットを振る
    @EventHandler
    public void Swing(PlayerInteractEvent e){
        if((e.getAction()== Action.LEFT_CLICK_AIR||e.getAction()== Action.LEFT_CLICK_BLOCK)&&e.getPlayer().getInventory().getItemInMainHand().getType()==Material.STICK){
            Player p=e.getPlayer();
            new Swing(p);
            logger.info(e.getPlayer().getUniqueId()+"：Swing");
        }
    }

    //ボールを投げる


    //ボールが跳ねる
    @EventHandler
    public void BoundBall(ProjectileHitEvent e){
        if(e.getEntity().getType()==EntityType.SNOWBALL&&e.getHitEntity()!=null&&e.getHitEntity().getType()==EntityType.PLAYER){
            e.getEntity().remove();
            Player p=((Player) e.getHitEntity());
            p.getInventory().addItem(new ItemStack(Material.SNOWBALL, 1));
            catchmap.get((Snowball) e.getEntity()).stop();
            hokyulist.add(p);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,Integer.MAX_VALUE,9,false,false));
            new BukkitRunnable(){
                @Override
                public void run() {
                    p.removePotionEffect(PotionEffectType.BLINDNESS);
                    hokyulist.remove(p);
                }
            }.runTaskLater(plugin,(long)((100-EachPlayerInfo.getPlayerInfo(p).getHokyu())*0.3));
        }else if(e.getEntity().getType()==EntityType.SNOWBALL&&e.getHitEntity()==null) {
            catchmap.get((Snowball) e.getEntity()).stop();
            if(e.getHitBlock().hasMetadata("stand")) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "！！ホームラン！！");
                return;
            }else if(e.getHitBlock().hasMetadata("foul")) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "ファール");
                return;
            }
            Snowball ball = (Snowball) e.getEntity();
            Vector vec=ball.getVelocity();
            int bound=0;
            List<MetadataValue> values = ball.getMetadata("bound");
            for (MetadataValue v : values) {
                // 名前を比較して同じプラグインか確認
                if (v.getOwningPlugin().getName().equals(plugin.getName())) {
                    // 同じなら値をセットしてループ抜ける
                    bound=v.asInt();
                    break;
                }
            }
            if(bound==5){
                ball.getWorld().dropItem(ball.getLocation(),new ItemStack(Material.SNOWBALL,1));
            }else {
                BlockFace face=e.getHitBlockFace();
                Snowball newball = (Snowball) ball.getWorld().spawnEntity(ball.getLocation(), EntityType.SNOWBALL);
                if(face==BlockFace.NORTH||face==BlockFace.SOUTH){
                    newball.setVelocity(new Vector(vec.getX(), vec.getY(), -vec.getZ()).multiply(0.25));
                    newball.setMetadata("bound", new FixedMetadataValue(plugin, bound));
                }else if(face==BlockFace.WEST||face==BlockFace.EAST){
                    newball.setVelocity(new Vector(-vec.getX(), vec.getY(), vec.getZ()).multiply(0.25));
                    newball.setMetadata("bound", new FixedMetadataValue(plugin, bound));
                }else if(face==BlockFace.DOWN){
                    newball.setVelocity(new Vector(vec.getX(), -vec.getY(), vec.getZ()).multiply(0.25));
                    newball.setMetadata("bound", new FixedMetadataValue(plugin, bound));
                }else if(face==BlockFace.UP){
                    double yvec=vec.getY();
                    newball.setVelocity(new Vector(vec.getX(), -0.35*yvec, vec.getZ()).multiply(0.9));
                    newball.setMetadata("bound", new FixedMetadataValue(plugin, bound + 1));
                }
                newball.setGlowing(true);
                catchmap.put(newball,new CatchTimer(newball,false,false));
            }
        }
    }
}
