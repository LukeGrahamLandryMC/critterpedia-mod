package ca.lukegrahamlandry.critterpedia.content.client;

import ca.lukegrahamlandry.critterpedia.ModMain;
import ca.lukegrahamlandry.critterpedia.content.client.models.ModdedBatModel;
import ca.lukegrahamlandry.critterpedia.content.init.EntityInit;
import ca.lukegrahamlandry.critterpedia.content.client.render.ModdedBatRender;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = ModMain.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        for (String rl : EntityInit.bats){
            byRL(rl, (ctx) -> new ModdedBatRender(ctx, (ctxx) -> new ModdedBatModel(ctxx, rl)));
        }
    }

    public static void byRL(String registryName, EntityRendererProvider renderer){
        EntityRenderers.register(ForgeRegistries.ENTITIES.getValue(new ResourceLocation(ModMain.MOD_ID, registryName)), renderer);
    }

    @SubscribeEvent
    public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event){
        for (String rl : EntityInit.bats){
            makeLayer(event, rl, ModdedBatModel.getModelFor(rl));
        }
    }

    public static void makeLayer(EntityRenderersEvent.RegisterLayerDefinitions event, String registryName, Supplier<LayerDefinition> supplier){
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(ModMain.MOD_ID, registryName), "main"), supplier);
    }
}