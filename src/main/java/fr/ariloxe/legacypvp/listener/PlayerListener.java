package fr.ariloxe.legacypvp.listener;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Consumable;
import io.papermc.paper.datacomponent.item.FoodProperties;
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Ariloxe
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent){
        playerJoinEvent.getPlayer().getAttribute(Attribute.ATTACK_SPEED).setBaseValue(20);
    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent playerItemHeldEvent){
        ItemStack itemStack = playerItemHeldEvent.getPlayer().getInventory().getItem(playerItemHeldEvent.getNewSlot());
        if(itemStack == null || itemStack.hasData(DataComponentTypes.FOOD))
            return;

        itemStack.setData(DataComponentTypes.CONSUMABLE, Consumable.consumable()
                .consumeSeconds(72000)
                .animation(ItemUseAnimation.BLOCK)
                .hasConsumeParticles(false)
                .build());
        itemStack.setData(DataComponentTypes.FOOD, FoodProperties.food()
                .canAlwaysEat(true)
                .saturation(99)
                .nutrition(99)
                .build());
    }


}
