package com.wolfmc.plugin.landsChatAdapter

import me.angeschossen.lands.api.LandsIntegration
import me.angeschossen.lands.api.player.chat.ChatMode
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class LandsChatAdapter : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        val api = LandsIntegration.of(this)
        Bukkit.getPluginCommand("lands-chat")?.setExecutor { sender, command, label, args ->
            if (sender !is Player) return@setExecutor false
            val mode = args.getOrNull(0) ?: return@setExecutor false
            val chatMode = if (mode.equals("join", true)) ChatMode.LAND else null
            api.getLandPlayer(sender.uniqueId).chatMode = chatMode
            true
        }
        Bukkit.getPluginCommand("nations-chat")?.setExecutor { sender, command, label, args ->
            if (sender !is Player) return@setExecutor false
            val mode = args.getOrNull(0) ?: return@setExecutor false
            val chatMode = if (mode.equals("join", true)) ChatMode.NATION else null
            api.getLandPlayer(sender.uniqueId).chatMode = chatMode
            true
        }
    }

    override fun onDisable() {
        Bukkit.getPluginCommand("lands-chat")?.setExecutor(null)
        Bukkit.getPluginCommand("nations-chat")?.setExecutor(null)
    }
}
