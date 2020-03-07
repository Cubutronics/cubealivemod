package net.cubealive.cubealivemod.client;

import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.cubealive.cubealivemod.gui.girlfriend.GirlfriendGUIMain;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class ClientHandler {
    public static void openGirlfriendGUI(PlayerEntity player, GirlfriendEntity gfEntity) {
        GirlfriendGUIMain girlfriendGUI = new GirlfriendGUIMain(new StringTextComponent("GUI"));
        girlfriendGUI.setPlayer(player);
        girlfriendGUI.setGirlfriend(gfEntity);
        Minecraft.getInstance().displayGuiScreen(girlfriendGUI);
    }
}
