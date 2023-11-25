package at.hannibal2.skyhanni.utils.tracker

import at.hannibal2.skyhanni.config.Storage
import at.hannibal2.skyhanni.test.PriceSource
import at.hannibal2.skyhanni.utils.ItemUtils.nameWithEnchantment
import at.hannibal2.skyhanni.utils.KeyboardManager
import at.hannibal2.skyhanni.utils.LorenzUtils
import at.hannibal2.skyhanni.utils.LorenzUtils.addAsSingletonList
import at.hannibal2.skyhanni.utils.LorenzUtils.addSelector
import at.hannibal2.skyhanni.utils.LorenzUtils.sortedDesc
import at.hannibal2.skyhanni.utils.NEUInternalName
import at.hannibal2.skyhanni.utils.NEUInternalName.Companion.asInternalName
import at.hannibal2.skyhanni.utils.NEUItems.getItemStack
import at.hannibal2.skyhanni.utils.NumberUtil
import at.hannibal2.skyhanni.utils.NumberUtil.addSeparators
import at.hannibal2.skyhanni.utils.StringUtils.removeColor
import at.hannibal2.skyhanni.utils.renderables.Renderable
import kotlin.time.Duration.Companion.seconds

class SkyHanniItemTracker<Data : ItemTrackerData>(
    name: String,
    createNewSession: () -> Data,
    getStorage: (Storage.ProfileSpecific) -> Data,
    drawDisplay: (Data) -> List<List<Any>>,
) : SkyHanniTracker<Data>(name, createNewSession, getStorage, drawDisplay) {

    companion object {
        val SKYBLOCK_COIN by lazy { "SKYBLOCK_COIN".asInternalName() }
    }

    private var lastClickDelay = 0L

    fun addCoins(coins: Int) {
        addItem(SKYBLOCK_COIN, coins)
    }

    fun addItem(internalName: NEUInternalName, stackSize: Int) {
        modify {
            it.additem(internalName, stackSize)
        }
    }

    fun addPriceFromButton(lists: MutableList<List<Any>>) {
        if (isInventoryOpen()) {
            lists.addSelector<PriceSource>(
                "",
                getName = { type -> type.displayName },
                isCurrent = { it.ordinal == config.priceFrom },
                onChange = {
                    config.priceFrom = it.ordinal
                    update()
                }
            )
        }
    }

    fun drawItems(
        data: Data,
        filter: (NEUInternalName) -> Boolean,
        lists: MutableList<List<Any>>
    ): Double {
        var profit = 0.0
        val items = mutableMapOf<Renderable, Long>()
        for ((internalName, itemProfit) in data.items) {
            if (!filter(internalName)) continue

            val amount = itemProfit.totalAmount
            val pricePer =
                if (internalName == SKYBLOCK_COIN) 1.0 else data.getCustomPricePer(internalName)
            val price = (pricePer * amount).toLong()
            val displayAmount = if (internalName == SKYBLOCK_COIN) itemProfit.timesGained else amount

            val cleanName = if (internalName == SKYBLOCK_COIN) {
                data.getCoinName(itemProfit)
            } else {
                internalName.getItemStack().nameWithEnchantment ?: error("no name for $internalName")
            }

            val priceFormat = NumberUtil.format(price)
            val hidden = itemProfit.hidden
            val newDrop = itemProfit.lastTimeUpdated.passedSince() < 10.seconds && config.showRecentDrops
            val numberColor = if (newDrop) "§a§l" else "§7"

            val text = " $numberColor${displayAmount.addSeparators()}x $name§7: §6$priceFormat"
            val (displayName, lore) = if (internalName == SKYBLOCK_COIN) {
                data.getCoinFormat(itemProfit, numberColor)
            } else text to buildLore(data, itemProfit, hidden, newDrop)

            val renderable = if (isInventoryOpen()) Renderable.clickAndHover(displayName, lore) {
                if (System.currentTimeMillis() > lastClickDelay + 150) {
                    if (KeyboardManager.isControlKeyDown()) {
                        data.items.remove(internalName)
                        LorenzUtils.chat("§e[SkyHanni] Removed $cleanName §efrom Fishing Frofit Tracker.")
                        lastClickDelay = System.currentTimeMillis() + 500
                    } else {
                        itemProfit.hidden = !hidden

                        lastClickDelay = System.currentTimeMillis()
                    }
                    update()
                }
            } else Renderable.string(displayName)
            if (isInventoryOpen() || !hidden) {
                items[renderable] = price
            }
            profit += price
        }

        for (text in items.sortedDesc().keys) {
            lists.addAsSingletonList(text)
        }
        return profit
    }

    private fun buildLore(
        data: Data,
        item: ItemTrackerData.TrackedItem,
        hidden: Boolean,
        newDrop: Boolean
    ) = buildList {
        if (internalName == SKYBLOCK_COIN) {
            addAll(data.getCoinDescription(item))
        } else {
            addAll(data.getDescription(item.timesGained))
        }
        add("")
        if (newDrop) {
            add("§aYou caught this item recently.")
            add("")
        }
        add("§eClick to " + (if (hidden) "show" else "hide") + "!")
        add("§eControl + Click to remove this item!")
    }

}
