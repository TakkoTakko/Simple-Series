package de.devcyntrix.simple.module.reward;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.ipvp.canvas.ClickInformation;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.slot.ClickOptions;
import org.ipvp.canvas.slot.Slot;
import org.ipvp.canvas.type.ChestMenu;
import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Data
public class RewardEntity {

    @JsonProperty("position")
    private Location position;

    @JsonProperty("type")
    private EntityType type;
    @JsonProperty("name")
    private String name;

    @JsonIgnore
    private LivingEntity livingEntity;

    @JsonCreator
    public RewardEntity(@JsonProperty("position") Location position, @JsonProperty("type") EntityType type, @JsonProperty("name") String name) {
        this.position = position;
        this.type = type;
        this.name = name;
    }

    public void interact(RewardModule module, Player player) {

        Menu menu = ChestMenu.builder(3)
                .title("Rewards")
                .build();

        ItemStack itemStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§r");
        itemStack.setItemMeta(itemMeta);

        for (int i = 0; i < menu.getDimensions().getArea(); i++) {
            Slot slot = menu.getSlot(i);
            slot.setItem(itemStack);
            slot.setClickOptions(ClickOptions.DENY_ALL);
        }

        RewardConfiguration configuration = module.getConfiguration();
        for (Reward reward : configuration.getRewards()) {
            Slot slot = menu.getSlot(reward.getSlot());
            slot.setItem(new ItemStack(Material.STICK));
            slot.setClickOptions(ClickOptions.DENY_ALL);
            slot.setClickHandler(new Slot.ClickHandler() {
                @Override
                public void click(Player player, ClickInformation clickInformation) {
                    try {
                        UserDataFile userDataFile = module.getPlayerRewardsCache().get(player.getUniqueId());
                        PlayerRewards playerRewards = userDataFile.getPlayerRewards();
                        Map<String, Long> gotRewards = playerRewards.getGotRewards();
                        Long time = gotRewards.get(reward.getId());
                        if (time != null && time >= System.currentTimeMillis()) {
                            PrettyTime prettyTime = new PrettyTime(Locale.GERMANY);
                            prettyTime.removeUnit(JustNow.class);
                            prettyTime.removeUnit(Millisecond.class);

                            List<Duration> durations = prettyTime.calculatePreciseDuration(new Date(time));
                            player.sendMessage(prettyTime.formatDuration(durations));
                            // Send remaining message
                            return;
                        }
                        // Give reward.
                        player.sendMessage("Give");

                        gotRewards.put(reward.getId(), System.currentTimeMillis() + reward.getDuration());
                        userDataFile.save();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        menu.open(player);
    }

    public LivingEntity respawn() {
        if (this.livingEntity != null)
            this.livingEntity.remove();

        this.livingEntity = (LivingEntity) position.getWorld().spawnEntity(position, type);
        this.livingEntity.setInvulnerable(true);
        this.livingEntity.setCustomName(name);
        this.livingEntity.setCustomNameVisible(true);
        this.livingEntity.setSilent(true);
        this.livingEntity.setAI(false);
        this.livingEntity.setCollidable(false);
        this.livingEntity.setRemoveWhenFarAway(false);
        return this.livingEntity;
    }

    public void remove() {
        if (this.livingEntity != null)
            this.livingEntity.remove();
    }

}
