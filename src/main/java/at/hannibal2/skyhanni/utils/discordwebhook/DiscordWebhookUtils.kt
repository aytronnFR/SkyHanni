package at.hannibal2.skyhanni.utils.discordwebhook

import at.hannibal2.skyhanni.SkyHanniMod
import at.hannibal2.skyhanni.features.garden.pests.PestAPI
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.URL
import java.nio.charset.StandardCharsets
import java.time.Instant
import javax.net.ssl.HttpsURLConnection

object DiscordWebhookUtils {

    val config get() = SkyHanniMod.feature.discord
    val gson get() = Gson()

    fun sendWebhookNotification(content: DiscordMessage) {
        val webhookUrl = config.webhookUrl.get()
        sendWebhook(content, webhookUrl)
    }

    fun sendWebhookChat(message: String) {
        val discordMessage = DiscordMessage(
            content = "",
            tts = false,
            embeds = listOf(
                Embed(
                    title = "",
                    description = message,
                    color = 2354139,
                    fields = listOf(),
                    timestamp = ""
                )
            ),
            components = listOf(),
            actions = mapOf(),
            username = "Chat",
            avatar_url = "https://user-images.githubusercontent.com/49322497/70186527-61601080-16ec-11ea-8e0e-49c0f5e4edde.png"
        )
        val webhookUrl = config.chatWebhookUrl.get()
        sendWebhook(discordMessage, webhookUrl)
    }

    private fun sendWebhook(content: DiscordMessage, webhookUrl: String) {
        if (!config.enabled.get()) {
            return
        }

        if (webhookUrl.isEmpty()) {
            return
        }
        SkyHanniMod.coroutineScope.launch {
            try {
                val url = URL(webhookUrl)
                val connection = url.openConnection() as HttpsURLConnection
                connection.addRequestProperty("Content-Type", "application/json")
                connection.addRequestProperty("User-Agent", "Java-DiscordWebhook")
                connection.doOutput = true
                connection.requestMethod = "POST"
                connection.outputStream.use { stream ->
                    stream.write(gson.toJson(content).toByteArray(StandardCharsets.UTF_8))
                    stream.flush()
                }
                connection.inputStream.close()
                connection.disconnect()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
