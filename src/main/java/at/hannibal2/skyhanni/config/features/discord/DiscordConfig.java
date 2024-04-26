package at.hannibal2.skyhanni.config.features.discord;

import at.hannibal2.skyhanni.config.FeatureToggle;
import com.google.gson.annotations.Expose;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorBoolean;
import io.github.notenoughupdates.moulconfig.annotations.ConfigEditorText;
import io.github.notenoughupdates.moulconfig.annotations.ConfigOption;
import io.github.notenoughupdates.moulconfig.observer.Property;

public class DiscordConfig {

    @Expose
    @ConfigOption(name = "Enable Discord Webhook", desc = "Allows you to send messages to a Discord channel.")
    @ConfigEditorBoolean
    @FeatureToggle
    public Property<Boolean> enabled = Property.of(false);

    @Expose
    @ConfigOption(name = "Webhook Url", desc = "The URL of the Discord webhook to send messages to.")
    @ConfigEditorText
    public Property<String> webhookUrl = Property.of("");

    @Expose
    @ConfigOption(name = "Enable Discord chat", desc = "Allows you to send chat messages to a Discord channel.")
    @ConfigEditorBoolean
    @FeatureToggle
    public Property<Boolean> chatDiscordEnabled = Property.of(false);

    @Expose
    @ConfigOption(name = "Chat Webhook Url", desc = "Chat messages will be sent to this webhook. Leave empty to use the main webhook.")
    @ConfigEditorText
    public Property<String> chatWebhookUrl = Property.of("");
}
