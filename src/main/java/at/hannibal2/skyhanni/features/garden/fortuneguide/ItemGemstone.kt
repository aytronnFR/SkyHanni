package at.hannibal2.skyhanni.features.garden.fortuneguide

import at.hannibal2.skyhanni.features.garden.fortuneguide.FarmingGemstone.FINE_PERIDOT
import at.hannibal2.skyhanni.features.garden.fortuneguide.FarmingGemstone.FLAWED_PERIDOT
import at.hannibal2.skyhanni.features.garden.fortuneguide.FarmingGemstone.FLAWLESS_PERIDOT
import at.hannibal2.skyhanni.features.garden.fortuneguide.FarmingGemstone.PERFECT_PERIDOT
import at.hannibal2.skyhanni.features.garden.fortuneguide.FarmingGemstone.ROUGH_PERIDOT
import at.hannibal2.skyhanni.utils.SkyBlockItemModifierUtils
import at.hannibal2.skyhanni.utils.SkyBlockItemModifierUtils.getItemId
import net.minecraft.item.ItemStack

enum class ItemGemstone(
    val itemId: String,
    val nbPeridotSlot: Int,
    val itemType: FarmingItems
) {
    //CACTUS TOOLS
    CACTUS_KNIFE("CACTUS_KNIFE", 2, FarmingItems.CACTUS),

    //MUSHROOM TOOLS
    FUNGI_CUTTER("FUNGI_CUTTER", 2, FarmingItems.MUSHROOM),

    //COCOA TOOLS
    COCO_CHOPPER("COCO_CHOPPER", 2, FarmingItems.COCOA_BEANS),

    //PUMPKIN TOOLS
    PUMPKIN_DICER_3("PUMPKIN_DICER_3", 2, FarmingItems.PUMPKIN),
    PUMPKIN_DICER_2("PUMPKIN_DICER_2", 1, FarmingItems.PUMPKIN),

    //MELON TOOLS
    MELON_DICER_3("MELON_DICER_3", 2, FarmingItems.MELON),
    MELON_DICER_2("MELON_DICER_2", 1, FarmingItems.MELON),

    //WHEAT TOOLS
    THEORETICAL_HOE_WHEAT_3("THEORETICAL_HOE_WHEAT_3", 3, FarmingItems.WHEAT),
    THEORETICAL_HOE_WHEAT_2("THEORETICAL_HOE_WHEAT_2", 2, FarmingItems.WHEAT),
    THEORETICAL_HOE_WHEAT_1("THEORETICAL_HOE_WHEAT_1", 1, FarmingItems.WHEAT),

    //POTATO TOOLS
    THEORETICAL_HOE_POTATO_3("THEORETICAL_HOE_POTATO_3", 3, FarmingItems.POTATO),
    THEORETICAL_HOE_POTATO_2("THEORETICAL_HOE_POTATO_2", 2, FarmingItems.POTATO),
    THEORETICAL_HOE_POTATO_1("THEORETICAL_HOE_POTATO_1", 1, FarmingItems.POTATO),

    //SUGAR CANE TOOLS
    THEORETICAL_HOE_CANE_3("THEORETICAL_HOE_CANE_3", 3, FarmingItems.SUGAR_CANE),
    THEORETICAL_HOE_CANE_2("THEORETICAL_HOE_CANE_2", 2, FarmingItems.SUGAR_CANE),
    THEORETICAL_HOE_CANE_1("THEORETICAL_HOE_CANE_1", 1, FarmingItems.SUGAR_CANE),

    //NETHER WART TOOLS
    THEORETICAL_HOE_WARTS_3("THEORETICAL_HOE_WARTS_3", 3, FarmingItems.NETHER_WART),
    THEORETICAL_HOE_WARTS_2("THEORETICAL_HOE_WARTS_2", 2, FarmingItems.NETHER_WART),
    THEORETICAL_HOE_WARTS_1("THEORETICAL_HOE_WARTS_1", 1, FarmingItems.NETHER_WART),

    //CARROT TOOLS
    THEORETICAL_HOE_CARROT_3("THEORETICAL_HOE_CARROT_3", 3, FarmingItems.CARROT),
    THEORETICAL_HOE_CARROT_2("THEORETICAL_HOE_CARROT_2", 2, FarmingItems.CARROT),
    THEORETICAL_HOE_CARROT_1("THEORETICAL_HOE_CARROT_1", 1, FarmingItems.CARROT),

    //CROPIE ARMOR
    CROPIE_HELMET("CROPIE_HELMET", 1, FarmingItems.HELMET),
    CROPIE_CHESTPLATE("CROPIE_CHESTPLATE", 1, FarmingItems.CHESTPLATE),
    CROPIE_LEGGINGS("CROPIE_LEGGINGS", 1, FarmingItems.LEGGINGS),
    CROPIE_BOOTS("CROPIE_LEGGINGS", 1, FarmingItems.BOOTS),

    //SQUASH ARMOR
    SQUASH_HELMET("SQUASH_HELMET", 1, FarmingItems.HELMET),
    SQUASH_CHESTPLATE("SQUASH_CHESTPLATE", 1, FarmingItems.CHESTPLATE),
    SQUASH_LEGGINGS("SQUASH_LEGGINGS", 1, FarmingItems.LEGGINGS),
    SQUASH_BOOTS("SQUASH_LEGGINGS", 1, FarmingItems.BOOTS),

    //FERMENTO ARMOR
    FERMENTO_HELMET("FERMENTO_HELMET", 2, FarmingItems.HELMET),
    FERMENTO_CHESTPLATE("FERMENTO_CHESTPLATE", 2, FarmingItems.CHESTPLATE),
    FERMENTO_LEGGINGS("FERMENTO_LEGGINGS", 2, FarmingItems.LEGGINGS),
    FERMENTO_BOOTS("FERMENTO_LEGGINGS", 2, FarmingItems.BOOTS);

    companion object {
        operator fun get(item: ItemStack): ItemGemstone? {
            val itemId = item.getItemId()
            return itemId?.let { id ->
                entries.find { it.itemId == id }
            }
        }
    }
}
