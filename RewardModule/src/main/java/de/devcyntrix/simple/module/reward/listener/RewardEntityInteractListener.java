package de.devcyntrix.simple.module.reward.listener;

import de.devcyntrix.simple.module.reward.RewardEntity;
import de.devcyntrix.simple.module.reward.RewardModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

@RequiredArgsConstructor
public class RewardEntityInteractListener implements Listener {

    private final RewardModule module;

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {
        RewardEntity entity = module.getConfiguration().getEntity();
        if (entity == null)
            return;
        if (!event.getRightClicked().equals(entity.getLivingEntity()))
            return;
        event.setCancelled(true);
        entity.interact(module, event.getPlayer());
    }

}
