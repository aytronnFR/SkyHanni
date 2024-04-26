package at.hannibal2.skyhanni.features.discord

import at.hannibal2.skyhanni.SkyHanniMod
import at.hannibal2.skyhanni.events.LorenzChatEvent
import at.hannibal2.skyhanni.utils.discordwebhook.DiscordWebhookUtils
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.regex.Pattern

class DiscordChatApi {
    val config get() = SkyHanniMod.feature.discord

    private val STRIP_COLOR_PATTERN: Pattern = Pattern.compile("(?i)" + '§'.toString() + "[0-9A-FK-ORX]")

    @SubscribeEvent
    fun onChat(event: LorenzChatEvent) {
        if (!config.chatDiscordEnabled.get()) return
        DiscordWebhookUtils.sendWebhookChat(stripColor(event.message))
    }

    fun stripColor(input: String?): String {
        return if (input == null) "" else STRIP_COLOR_PATTERN.matcher(input).replaceAll("")
    }
}
