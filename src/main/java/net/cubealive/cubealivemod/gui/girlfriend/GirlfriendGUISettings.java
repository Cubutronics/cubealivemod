package net.cubealive.cubealivemod.gui.girlfriend;

import net.cubealive.cubealivemod.CubealiveMod;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendEntity;
import net.cubealive.cubealivemod.entity.girlfriend.GirlfriendRenderer;
import net.cubealive.cubealivemod.entity.girlfriend.RenderGirlfriendFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(value=Dist.CLIENT)
public class GirlfriendGUISettings extends Screen {

    @ObjectHolder(CubealiveMod.MODID + ":girlfriend")
    public static EntityType<GirlfriendEntity> GIRLFRIENDENTITY;

    PlayerEntity player = null;
    GirlfriendEntity girlfriend = null;
    private TextFieldWidget textFieldName;
    private String followString = "Bleib hier!";
    private String girlfriend_name = "Dev's Girlfriend";
    EntityType<? extends TameableEntity> EntityType;


    public GirlfriendGUISettings(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks) {
        final int halfW = this.width / 2;
        final int halfH = this.height / 2;
        this.renderBackground();
        this.textFieldName.render(mouseX, mouseX, partialTicks);
        this.drawSizeString(this.font,"Settings",halfW-40,halfH-80,0xFFFFFF,2F);
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void init() {


        final int halfW = this.width / 2;
        final int halfH = this.height / 2;

        if(girlfriend.isSitting())  followString = "Follow me !";
        else                        followString = "Stay here !";




        this.textFieldName = new TextFieldWidget(this.font, halfW-80, halfH-10, 95, 20, "");
        this.children.add(this.textFieldName);

        // "Refresh Mini Model" button rebuilds the tile's MiniModel;
        this.addButton(new ExtendedButton(halfW+20, halfH-10, 70, 20, I18n.format("Change name"),
                $ -> {
                    if(!textFieldName.getText().isEmpty()){
                        girlfriend.setCustomName(new StringTextComponent(textFieldName.getText()));
                    } else {
                        girlfriend.setCustomName(new StringTextComponent(player.getScoreboardName()+"'s Girlfriend"));
                    }
                }
        ));
        this.addButton(new ExtendedButton(halfW+20, halfH+20, 70, 20, I18n.format("Create GF"),
                $ -> {
                   //GirlfriendRenderer.setEntityTexture(new ResourceLocation(CubealiveMod.MODID,"textures/entity/girlfriend/girlfriend2.png"));
                   //RenderingRegistry.registerEntityRenderingHandler(GIRLFRIENDENTITY, RenderGirlfriendFactory.INSTANCE);
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
        this.textFieldName.tick();
    }

    @Override
    public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
        this.textFieldName.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
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
