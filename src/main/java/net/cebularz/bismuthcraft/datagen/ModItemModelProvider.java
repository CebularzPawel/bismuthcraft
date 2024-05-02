package net.cebularz.bismuthcraft.datagen;

import net.cebularz.bismuthcraft.bismuthcraft;
import net.cebularz.bismuthcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, bismuthcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BISMUTH);
        simpleItem(ModItems.BISMUTH_BALL);
        simpleItem(ModItems.PINEAPPLE_SLICE);
        simpleItem(ModItems.PACKED_MUD_BALL);
        simpleItem(ModItems.SINKING_MUD_BUCKET);

    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(bismuthcraft.MOD_ID,"item/" + item.getId().getPath()));
    }
}
