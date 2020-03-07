package net.cubealive.cubealivemod.gui.girlfriend;

import net.cubealive.cubealivemod.CubealiveMod;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(value=Dist.CLIENT)
public class GirlfriendGUICommands extends Screen {

    PlayerEntity player = null;
    GirlfriendEntity girlfriend = null;
    //private TextFieldWidget mySupaTextfield;
    private String followString = "Bleib hier!";
    private String girlfriend_name = "Dev's Girlfriend";

    public GirlfriendGUICommands(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        final int halfW = this.width / 2;
        final int halfH = this.height / 2;
        this.renderBackground();
        //this.mySupaTextfield.render(mouseX, mouseX, partialTicks);
        this.drawSizeString(this.font,"Commands",halfW-45,halfH-80,0xFFFFFF,2F);
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void init() {


        final int halfW = this.width / 2;
        final int halfH = this.height / 2;

        if(girlfriend.isSitting())  followString = "Follow me !";
        else                        followString = "Stay here !";




        //this.mySupaTextfield = new TextFieldWidget(this.font, halfW-80, halfH-10, 95, 20, "");
        //this.children.add(this.mySupaTextfield);

        // "Refresh Mini Model" button rebuilds the tile's MiniModel;
        this.addButton(new ExtendedButton(halfW-80, halfH+20, 170, 20, I18n.format(followString),
                $ -> {
                    if(girlfriend.isSitting()){
                        girlfriend.sit();
                        this.player.sendMessage(new StringTextComponent("Ok ! I'll follow you :)"));
                        Minecraft.getInstance().displayGuiScreen(null);
                    } else {
                        girlfriend.sit();
                        this.player.sendMessage(new StringTextComponent("Ok ! I'll stay here :)"));
                        Minecraft.getInstance().displayGuiScreen(null);
                    }
                }
        ));
        // "Done" button exits the GUI
        this.addButton(new ExtendedButton(halfW-80, halfH+50, 170, 20, "Done",
                $ -> this.minecraft.displayGuiScreen(null)
        ));
        this.insertText("test",false);
        super.init();
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Don't pause the game when this screen is open
    }

    @Override
    public void renderBackground() {
        final int halfW = this.width / 2;
        final int halfH = this.height / 2;
        final ResourceLocation bg = new ResourceLocation(CubealiveMod.MODID,"textures/gui/girlfriendbg.png");
        Minecraft.getInstance().getTextureManager().bindTexture(bg);
        int scaleHeight =  Minecraft.getInstance().getMainWindow().getScaledHeight();
        int scaledWidth =  Minecraft.getInstance().getMainWindow().getScaledWidth();
        blit((int)(scaledWidth/2)-140, (int)(scaleHeight/2)-90, 0, 0, 280, 190);
        //super.renderBackground();
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        //this.mySupaTextfield.tick();
    }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        //this.mySupaTextfield.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
        return super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
    }

    public void setPlayer(PlayerEntity player){
        this.player = player;
    }
    public void setGirlfriend(GirlfriendEntity girlfriend){
        this.girlfriend = girlfriend;
    }

    public void drawSizeString(FontRenderer fontRendererIn, String text, int x, int y, int color, float size) {
        GL11.glScalef(size,size,size);
        float mSize = (float)Math.pow(size,-1);
        this.drawString(fontRendererIn,text,(int)(x/size),(int)(y/size),color);
        GL11.glScalef(mSize,mSize,mSize);
    }

}
