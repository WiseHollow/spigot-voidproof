package net.johnbrooks.voidproof;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class VoidListener implements Listener
{
    @EventHandler
    public void onPlayerHurt(EntityDamageEvent event)
    {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.VOID &&
                VoidProof.getPlugin().isEnabledWorld(event.getEntity().getWorld().getName()))
        {
            event.setCancelled(true);
            event.setDamage(0d);
            event.getEntity().teleport(VoidProof.getPlugin().getTransportTo());
        }
    }
}
