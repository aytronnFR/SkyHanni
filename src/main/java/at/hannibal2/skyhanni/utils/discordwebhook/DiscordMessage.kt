package at.hannibal2.skyhanni.utils.discordwebhook

data class DiscordMessage(
    val content: String,
    val tts: Boolean,
    val embeds: List<Embed>,
    val components: List<Any>,
    val actions: Map<String, String>,
    val username: String,
    val avatar_url: String
)

data class Embed(
    val title: String,
    val description: String,
    val color: Int,
    val fields: List<Field>,
    val timestamp: String
)

data class Field(
    val name: String,
    val value: String
)
