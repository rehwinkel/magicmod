package deerangle.magicmod.container;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegistry {

    public static ContainerType<WandTableContainer> WAND_TABLE;

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> event) {
        WAND_TABLE = register(WandTableContainer::new, "wand_table");
        event.getRegistry().registerAll(WAND_TABLE);
    }

    private static <T extends Container> ContainerType<T> register(ContainerType.IFactory<T> factory, String name) {
        ContainerType<T> ct = new ContainerType<T>(factory);
        ct.setRegistryName(name);
        return ct;
    }

}
