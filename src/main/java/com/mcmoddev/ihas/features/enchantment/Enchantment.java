package com.mcmoddev.ihas.features.enchantment;

import com.google.common.collect.Sets;
import com.mcmoddev.ihas.features.BlankIHasFeature;
import com.mcmoddev.ihas.features.IHASFeature;
import com.mcmoddev.ihas.features.IProxy;
import com.mcmoddev.ihas.features.enchantment.enchantments.ExplosiveEnchant;
import com.mcmoddev.ihas.features.enchantment.enchantments.WisdomEnchant;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Set;

@IHASFeature(featureID = "enchantment", name = "Enchantment", version = "1.0.0")
public class Enchantment extends BlankIHasFeature {

    @SidedProxy(clientSide = "com.mcmoddev.ihas.features.enchantment.EnchantmentClientProxy", serverSide = "com.mcmoddev.ihas.features.enchantment.EnchantmentServerProxy")
    public static IProxy PROXY;

    @Override
    public Set<String> getDependIds () {
        return Sets.newHashSet("core");
    }

    @Override
    public boolean canBeDisabled () {
        return true;
    }

    @Override
    public void preInit () {
        ExplosiveEnchant.register();
        WisdomEnchant.register();
    }

    @Override
    public void init () {
        super.init();
    }

    @Override
    public void postInit () {
        super.postInit();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IProxy getProxy () {
        return PROXY;
    }

	@Override
	public boolean hasEvents() {
		
		return true;
	}
	
    @SubscribeEvent
    public void onExperienceDrop (LivingExperienceDropEvent event) {
        event.setDroppedExperience((int) (event.getOriginalExperience() + event.getOriginalExperience() * 0.15 * EnchantmentHelper.getEnchantmentLevel(WisdomEnchant.ENCHANT, event.getAttackingPlayer().getHeldItemMainhand())));
    }

    @SubscribeEvent
    public void onEntityLivingFall (LivingFallEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        float distance = event.getDistance();
        if (entity instanceof EntityPlayer && distance > 5.0F && entity.isSneaking() && entity.getArmorInventoryList() instanceof List && !((List) entity.getArmorInventoryList()).isEmpty())
            for (ItemStack stack : entity.getArmorInventoryList()) {
                int lvl = EnchantmentHelper.getEnchantmentLevel(ExplosiveEnchant.ENCHANT, stack);
                if (stack != null && stack.getItem() instanceof ItemArmor && lvl > 0) {
                    float h = entity.getHealth();
                    entity.getEntityWorld().createExplosion(entity, entity.posX, entity.posY, entity.posZ, Math.min(distance / (8F / lvl), 5.0F), true);
                    entity.setHealth(h);
                }
            }
    }
}