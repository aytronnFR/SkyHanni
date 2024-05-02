package at.hannibal2.skyhanni.features.garden.fortuneguide

import at.hannibal2.skyhanni.utils.SkyBlockItemModifierUtils

enum class FarmingGemstone(
    val displayName: String,
    val gemstoneItem: String,
    val common: Double,
    val uncommon: Double,
    val rare: Double,
    val epic: Double,
    val legendary: Double,
    val mythic: Double,
) {
    ROUGH_PERIDOT("Rough Peridot", "ROUGH_PERIDOT_GEM", 0.5, 1.0, 1.5, 2.0, 2.5, 3.0),
    FLAWED_PERIDOT("Flawed Peridot", "FLAWED_PERIDOT_GEM", 1.0, 1.5, 2.0, 2.5, 3.0, 4.0),
    FINE_PERIDOT("Fine Peridot", "FINE_PERIDOT_GEM", 1.5, 2.0, 3.0, 4.0, 5.0, 6.0),
    FLAWLESS_PERIDOT("Flawless Peridot", "FLAWLESS_PERIDOT_GEM", 2.0, 3.0, 4.0, 5.0, 6.0, 8.0),
    PERFECT_PERIDOT("Perfect Peridot", "PERFECT_PERIDOT_GEM", 3.0, 4.0, 5.0, 6.0, 8.0, 10.0);

    companion object {
        operator fun get(gemstoneType: SkyBlockItemModifierUtils.GemstoneType, gemstoneQuality: SkyBlockItemModifierUtils.GemstoneQuality): FarmingGemstone? {
            return if (gemstoneType == SkyBlockItemModifierUtils.GemstoneType.PERIDOT) {
                when (gemstoneQuality) {
                    SkyBlockItemModifierUtils.GemstoneQuality.ROUGH -> ROUGH_PERIDOT
                    SkyBlockItemModifierUtils.GemstoneQuality.FLAWED -> FLAWED_PERIDOT
                    SkyBlockItemModifierUtils.GemstoneQuality.FINE -> FINE_PERIDOT
                    SkyBlockItemModifierUtils.GemstoneQuality.FLAWLESS -> FLAWLESS_PERIDOT
                    SkyBlockItemModifierUtils.GemstoneQuality.PERFECT -> PERFECT_PERIDOT
                }
            } else {
                null
            }
        }
    }
    operator fun get(index: Int, current: Double = 0.0): Double {
        return when (index) {
            0 -> this.common - current
            1 -> this.uncommon - current
            2 -> this.rare - current
            3 -> this.epic - current
            4 -> this.legendary - current
            5 -> this.mythic - current
            else -> 0.0
        }
    }
}
